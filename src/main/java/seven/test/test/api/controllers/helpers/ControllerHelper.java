package seven.test.test.api.controllers.helpers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import seven.test.test.api.exception.BadRequestException;
import seven.test.test.store.entities.UserEntitty;
import seven.test.test.store.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Transactional
public class ControllerHelper {
    UserRepository userRepository;

    public UserEntitty getUserOrThrowException(Long phone) {
        Optional<UserEntitty> savedUser = userRepository.findByPhone(phone);
        boolean isUserHave = savedUser.isPresent();
        if (isUserHave) {
            throw new BadRequestException(
                    String.format(
                            "User with \"%s\" doesn't exist.",
                            phone
                    ));
        }
        return null;
    }


}
