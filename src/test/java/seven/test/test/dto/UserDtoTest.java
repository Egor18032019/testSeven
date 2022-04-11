package seven.test.test.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seven.test.test.api.dto.UserDto;

import static seven.test.test.data.UserTestData.user_60001;

public class UserDtoTest {
    public static final String email = "email";
    public static final String surname = "surname";
    public static final String username = "username";
    public static final String patronymic = "patronymic";
    public static final Long phone = 8912L;

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
        Assertions.assertSame(result.getEmail(),email);
        Assertions.assertSame(result.getSurname(),surname);
        Assertions.assertSame(result.getUsername(), username);
        Assertions.assertSame(result.getPatronymic(),patronymic);
        Assertions.assertEquals(result.getPhone(),phone);
    }

}
