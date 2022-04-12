package seven.test.test.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seven.test.test.api.controllers.helpers.ControllerHelper;
import seven.test.test.api.dto.UserDto;
import seven.test.test.api.dto.UserDtoFactory;
import seven.test.test.api.exception.BadRequestException;
import seven.test.test.api.utils.Const;
import seven.test.test.store.entities.UserEntitty;
import seven.test.test.store.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@Tag(name = "Контроллер для записи данных в БД и получения их")
public class UserController {
    UserRepository userRepository;
    ControllerHelper controllerHelper;

    @PostMapping(Const.USER_URL)
    public ResponseEntity<UserDto> createUser(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "surname", required = true) String surname,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "patronymic", required = true) String patronymic,
            @RequestParam(value = "phone", required = true) String phone
    ) {
        Long parsePhone;
        try {
            parsePhone = Long.parseLong(phone);

        } catch (Exception e) {
            throw new BadRequestException(
                    String.format(
                            "Неправильный номер телефона - \"%s\".",
                            phone
                    ));
        }

        controllerHelper.getUserOrThrowException(parsePhone);

        final UserEntitty userForBD = UserEntitty.builder()
                .email(email)
                .surname(surname)
                .username(username)
                .patronymic(patronymic)
                .phone(parsePhone)
                .build();
        userRepository.saveAndFlush(userForBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDtoFactory.makeProjectDto(userForBD));

    }

    @GetMapping(Const.USER_URL)
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(value = "email", required = false) Optional<String> optionalEmail,
            @RequestParam(value = "surname", required = false) Optional<String> optionalSurname,
            @RequestParam(value = "username", required = false) Optional<String> optionalName,
            @RequestParam(value = "patronymic", required = false) Optional<String> optionalPatronymic,
            @RequestParam(value = "phone", required = false) Optional<String> optionalPhone
    ) {
        optionalPhone = optionalPhone.filter(prefixName -> !prefixName.trim().isEmpty());
        optionalEmail = optionalEmail.filter(prefixName -> !prefixName.trim().isEmpty());
        optionalSurname = optionalSurname.filter(prefixName -> !prefixName.trim().isEmpty());
        optionalName = optionalName.filter(prefixName -> !prefixName.trim().isEmpty());
        optionalPatronymic = optionalPatronymic.filter(prefixName -> !prefixName.trim().isEmpty());

        boolean havePhone = optionalPhone.isPresent();
        if (havePhone) {
            Long phone = Long.parseLong(optionalPhone.get());
            Optional<UserEntitty> userFromBD = userRepository.findByPhone(phone);
            boolean isHavePhoneFromUserBD = userFromBD.isPresent();
            if (isHavePhoneFromUserBD) {
                return ResponseEntity.status(HttpStatus.OK).
                        body(
                                userFromBD.stream()
                                        .map(UserDtoFactory::makeProjectDto)
                                        .collect(Collectors.toList())
                        );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(
                                null
                        );
            }
        }
        boolean haveEmail = optionalEmail.isPresent();
        if (haveEmail) {
            String email = optionalEmail.get();
            System.out.println("email " + email);
            List<UserEntitty> userWithEmail = userRepository.findAllByEmail(email);
            boolean haveAnyUserWithEmail = userWithEmail.isEmpty();
            if (!haveAnyUserWithEmail) {
                return ResponseEntity.status(HttpStatus.OK).
                        body(
                                userWithEmail.stream()
                                        .map(UserDtoFactory::makeProjectDto)
                                        .collect(Collectors.toList())
                        );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(
                                null
                        );
            }
        }
        boolean haveSurName = optionalSurname.isPresent();
        if (haveSurName) {
            String surname = optionalSurname.get();
            List<UserEntitty> userWithSurname = userRepository.findAllBySurname(surname);
            boolean haveAnyUserWithSurname = userWithSurname.isEmpty();
            if (!haveAnyUserWithSurname) {

                return ResponseEntity.status(HttpStatus.OK).
                        body(
                                userWithSurname.stream()
                                        .map(UserDtoFactory::makeProjectDto)
                                        .collect(Collectors.toList())
                        );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(
                                null
                        );
            }
        }
        boolean haveName = optionalName.isPresent();
        if (haveName) {
            String name = optionalName.get();
            List<UserEntitty> userWithName = userRepository.findAllByUsername(name);
            boolean haveAnyUserWithName = userWithName.isEmpty();
            if (!haveAnyUserWithName) {
                return ResponseEntity.status(HttpStatus.OK).
                        body(
                                userWithName.stream()
                                        .map(UserDtoFactory::makeProjectDto)
                                        .collect(Collectors.toList())
                        );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(
                                null
                        );
            }
        }
        boolean havePatronymic = optionalPatronymic.isPresent();
        if (havePatronymic) {
            String patronymic = optionalPatronymic.get();
            List<UserEntitty> userWithPatronymic = userRepository.findAllByPatronymic(patronymic);
            boolean haveAnyUserWithName = userWithPatronymic.isEmpty();
            if (!haveAnyUserWithName) {
                return ResponseEntity.status(HttpStatus.OK).
                        body(
                                userWithPatronymic.stream()
                                        .map(UserDtoFactory::makeProjectDto)
                                        .collect(Collectors.toList())
                        );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(
                                null
                        );
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(
                        null
                );
    }
}
