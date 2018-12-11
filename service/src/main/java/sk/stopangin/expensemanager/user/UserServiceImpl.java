package sk.stopangin.expensemanager.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import sk.stopangin.expensemanager.common.Transactional;


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
    @Transactional
    public UserDto create(UserDto userDto) {
        final User user = userDao.create(userMapper.fromDto(userDto));
        final UserDto userDto1 = userMapper.fromDomain(user);
        return userDto1;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        final User user = userDao.update(userMapper.fromDto(userDto));
        final UserDto userDto1 = userMapper.fromDomain(user);
        return userDto1;    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.fromDomain(userDao.findById(id));
    }
}
