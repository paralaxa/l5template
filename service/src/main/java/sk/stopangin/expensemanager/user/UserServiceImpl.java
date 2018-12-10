package sk.stopangin.expensemanager.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * Created by martin.cuchran on 12/6/2018.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private PlatformTransactionManager platformTransactionManager;
    private UserMapper userMapper;

    public UserServiceImpl(UserDao userDao, PlatformTransactionManager platformTransactionManager, UserMapper userMapper) {
        this.userDao = userDao;
        this.platformTransactionManager = platformTransactionManager;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto create(UserDto userDto) {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        final TransactionStatus transaction = platformTransactionManager.getTransaction(defaultTransactionDefinition);
        final User user = userDao.create(userMapper.fromDto(userDto));
        final UserDto userDto1 = userMapper.fromDomain(user);
        platformTransactionManager.commit(transaction);
        return userDto1;
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.fromDomain(userDao.findById(id));
    }
}
