package seven.test.test.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import seven.test.test.api.dto.UserDto;
import seven.test.test.api.dto.UserDtoFactory;
import seven.test.test.api.utils.Const;
import seven.test.test.data.UserTestData;
import seven.test.test.store.repositories.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static seven.test.test.data.UserTestData.user_60001;

public class UserControllerTest extends AbstractUserRestControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void registerNewUser() throws Exception {

        UserDto receivedUser = UserTestData.asUserDto(
                perform(
                        MockMvcRequestBuilders.post(Const.USER_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("email", user_60001.getEmail())
                                .param("surname", user_60001.getSurname())
                                .param("username", user_60001.getUsername())
                                .param("patronymic", user_60001.getPatronymic())
                                .param("phone", user_60001.getPhone().toString()))
                        .andExpect(status().isCreated())
                        .andReturn());
        // сравниваем то что хотели и то что в базе ?
        UserDto registeredUserDto = UserDtoFactory.makeProjectDto(
                userRepository.findByPhone(user_60001.getPhone())
                        .orElseGet(null)
                //как тут выдавать ?
        );
        UserTestData.assertEquals(receivedUser, registeredUserDto);
    }


}
