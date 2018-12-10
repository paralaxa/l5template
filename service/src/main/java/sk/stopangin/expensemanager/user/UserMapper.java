package sk.stopangin.expensemanager.user;

import org.mapstruct.Mapper;

/**
 * Created by martin.cuchran on 12/6/2018.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromDto(UserDto userDto);
    UserDto fromDomain(User user);
}
