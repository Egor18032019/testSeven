package seven.test.test.api.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class UserDto {

    @NonNull
    String email;
    @NonNull
    String surname;
    @NonNull
    String username;
    @NonNull
    String patronymic;
    @NonNull
    Long phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(email, userDto.email) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(username, userDto.username) &&
                Objects.equals(patronymic, userDto.patronymic) &&
                Objects.equals(phone, userDto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, surname, username, patronymic, phone);
    }
}