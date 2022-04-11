package seven.test.test.api.dto;

import org.springframework.stereotype.Component;
import seven.test.test.store.entities.UserEntitty;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoFactory {

    public static UserDto makeProjectDto(UserEntitty entity) {
        System.out.println(" makeProjectDto ");
        return UserDto.builder()
                .email(entity.getEmail())
                .surname(entity.getSurname())
                .username(entity.getUsername())
                .patronymic(entity.getPatronymic())
                .phone(entity.getPhone())
                .build();
    }

    public static List<UserDto> makeUserDtoFromListUserEntity(List<UserEntitty> users) {
        return users.stream().map(UserDtoFactory::makeProjectDto).collect(Collectors.toList());
    }
}