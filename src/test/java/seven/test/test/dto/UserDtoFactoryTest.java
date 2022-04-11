package seven.test.test.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seven.test.test.api.dto.UserDto;
import seven.test.test.api.dto.UserDtoFactory;

 import static seven.test.test.data.UserTestData.user_60001;

public class UserDtoFactoryTest {

    @Test
    void createNewUserDtoFromUserEntity() {
        UserDto result = UserDto
                .builder()
                .email(user_60001.getEmail())
                .surname(user_60001.getSurname())
                .username(user_60001.getUsername())
                .patronymic(user_60001.getPatronymic())
                .phone(user_60001.getPhone())
                .build();

        Assertions.assertEquals(result, UserDtoFactory.makeProjectDto(user_60001));
    }
}