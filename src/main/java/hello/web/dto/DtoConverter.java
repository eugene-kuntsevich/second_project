package hello.web.dto;

import hello.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with converters from {@link User} to {@link UserDto}
 */
public class DtoConverter {

    /**
     * Method for converting {@link User} to {@link UserDto}
     *
     * @param user input {@link User}
     * @return {@link UserDto}
     */
    public static UserDto convertUserToDto(User user) {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setCreationDate(user.getCreationDate());

        return dto;
    }

    /**
     * Method for converting {@link List<User>} to {@link List<UserDto>}
     *
     * @param users input list of {@link User}'s
     * @return {@link List<UserDto>}
     */
    public static List<UserDto> convertListUsersToDtos(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(convertUserToDto(user));
        }

        return dtos;
    }
}
