package sk.stopangin.expensemanager.user;

/**
 * Created by martin.cuchran on 12/6/2018.
 */
public interface UserResource {
    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    void delete(Long id);

    UserDto getById(Long id);

}
