package hello.web.dto;

import hello.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    public static UserDto convertUserToDto(User user) {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setCreationDate(user.getCreationDate());

        return dto;
    }

    public static List<UserDto> convertListUsersToDtos(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(convertUserToDto(user));
        }

        return dtos;
    }
}
