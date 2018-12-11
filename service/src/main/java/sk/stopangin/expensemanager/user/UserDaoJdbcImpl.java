package sk.stopangin.expensemanager.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by martin.cuchran on 12/6/2018.
 */
@Repository
public class UserDaoJdbcImpl implements UserDao, RowMapper<User> {
    private static final String INSERT_INTO_USER = "INSERT INTO user VALUES(?,?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id=? LIMIT 1";
    private static final String UPDATE_USER = "UPDATE user SET username=? WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    public UserDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        jdbcTemplate.update(INSERT_INTO_USER, user.getId(), user.getUsername());
        return user;
    }

    @Override
    public User update(User user) {
        User userInDb = null;
        try {
            userInDb = findById(user.getId());
        } catch (Exception e) {
        }
        if (userInDb == null) {
            return create(user);
        } else {
            jdbcTemplate.update(UPDATE_USER, user.getUsername(), user.getId());
            return user;
        }


    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, this::mapRow, id);
    }

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        return user;
    }
}
