package sk.stopangin.expensemanager.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by martin.cuchran on 12/6/2018.
 */
@RestController
public class UserResourceImpl implements UserResource{

    private UserService userService;

    public UserResourceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    @GetMapping
    public UserDto getById(Long id) {
        return userService.getById(id);
    }
}
