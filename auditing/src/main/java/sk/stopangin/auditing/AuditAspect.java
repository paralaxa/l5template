package sk.stopangin.auditing;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Aspect
@Component
@Order(10)
public class AuditAspect {
    private static final Logger log = Logger.getLogger(AuditAspect.class);

    private AuditDao auditDao;
    private PlatformTransactionManager transactionManager;


    public AuditAspect(AuditDao auditDao, PlatformTransactionManager transactionManager) {
        this.auditDao = auditDao;
        this.transactionManager = transactionManager;
    }

    @Around("@annotation(Auditable)")
    public Object persistAudit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        Object result = null;
        log.info("starting transaction");
        log.info("Auditing for:" + proceedingJoinPoint.getTarget().getClass().getName() + "#" + proceedingJoinPoint.getSignature().getName() + " in progress.");
        try {
            Audit audit = new Audit();
            audit.setId(1l);
            audit.setAuditedEntityClassname(proceedingJoinPoint.getArgs()[0].getClass().getName());
            result = proceedingJoinPoint.proceed();
            auditDao.create(audit);
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            log.error("Error during auditing", e);
            System.out.println(txStatus);
            transactionManager.rollback(txStatus);
        }
        return result;
    }
}
