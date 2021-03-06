package seven.test.test.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import seven.test.test.store.entities.UserEntitty;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntitty, Long> {

    Optional<UserEntitty> findByPhone(Long phone);

    List<UserEntitty> findAllByEmail(String email);

    List<UserEntitty> findAllBySurname(String surname);

    List<UserEntitty> findAllByUsername(String name);

    List<UserEntitty> findAllByPatronymic(String patronymic);

}
