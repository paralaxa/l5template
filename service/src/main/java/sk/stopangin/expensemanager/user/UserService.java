package sk.stopangin.expensemanager.user;

/**
 * Created by martin.cuchran on 12/6/2018.
 */
public interface UserService {
   UserDto create(UserDto userDto);
   UserDto getById(Long id);
}
