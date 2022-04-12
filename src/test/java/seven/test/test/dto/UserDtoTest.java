package seven.test.test.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seven.test.test.api.dto.UserDto;

import static seven.test.test.data.UserTestData.user_60001;

public class UserDtoTest {

    @Test
    void createNewUserDtoObject() {
        UserDto result = UserDto
                .builder()
                .email(user_60001.getEmail())
                .surname(user_60001.getSurname())
                .username(user_60001.getUsername())
                .patronymic(user_60001.getPatronymic())
                .phone(user_60001.getPhone())
                .build();
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getPhone());
        Assertions.assertNotNull(result.getUsername());
        Assertions.assertSame(result.getEmail(),user_60001.getEmail());
        Assertions.assertSame(result.getSurname(),user_60001.getSurname());
        Assertions.assertSame(result.getUsername(), user_60001.getUsername());
        Assertions.assertSame(result.getPatronymic(),user_60001.getPatronymic());
        Assertions.assertEquals(result.getPhone(),user_60001.getPhone());
    }

}
