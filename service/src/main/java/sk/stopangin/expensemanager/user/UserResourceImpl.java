package sk.stopangin.expensemanager.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by martin.cuchran on 12/6/2018.
 */
@RestController
@RequestMapping("user")
public class UserResourceImpl implements UserResource {

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
    @PutMapping("{id}")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable("id") Long id) {
        userDto.setId(id);
        return userService.update(userDto);
    }

    @Override
    public void delete(Long id) {

    }

    //GET root/user/djksahdkssa/id
    @Override
    @GetMapping("{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
}
