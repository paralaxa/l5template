package sk.stopangin.expensemanager.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by martin.cuchran on 12/11/2018.
 */
@Aspect
@Component
@Order(1000)
public class TransactionalAspect {
    private static final Logger log = Logger.getLogger(TransactionalAspect.class);
    private PlatformTransactionManager platformTransactionManager;

    public TransactionalAspect(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    @Around("@annotation(Transactional)")
    public Object runInTransaction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
        txDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        log.info("starting transaction");
        final TransactionStatus transaction = platformTransactionManager.getTransaction(txDef);
        try {
            result = proceedingJoinPoint.proceed();
            log.info("committing transaction");
            platformTransactionManager.commit(transaction);
        } catch (Throwable throwable) {
            log.info("rolling back transaction");
            log.error(throwable);
            platformTransactionManager.rollback(transaction);
        }
        return result;
    }
}
