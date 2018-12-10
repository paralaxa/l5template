package sk.stopangin.expensemanager.user;

/**
 * Created by martin.cuchran on 12/6/2018.
 */
public interface UserDao {
    User create(User user);

    User update(User user);

    void delete(Long id);

    User findById(Long id);
}
