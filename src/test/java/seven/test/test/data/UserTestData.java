package seven.test.test.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.MvcResult;
import seven.test.test.api.dto.UserDto;
import seven.test.test.store.entities.UserEntitty;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;


public class UserTestData {

    public static final long USER_60002_ID = 60002;

    public static final UserEntitty user_60001 = new UserEntitty(USER_60002_ID, "goro4@mail", "P", "E","V", 8912L);
    final static ObjectMapper objectMapper = new ObjectMapper();

    public static UserDto asUserDto(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return  readValue(jsonActual, UserDto.class);
    }

    public static <T> T readValue(String json, Class<T> clazz) throws JsonProcessingException {


        return objectMapper.readValue(json, clazz);
    }

    public static <T> void assertEquals(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }
}

