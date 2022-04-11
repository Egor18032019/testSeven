package seven.test.test.store.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)//все поля приватные
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntitty {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
            Long id;
    @Column()
    String email;
    @Column()
    String surname;
    @Column()
    String username;
    @Column()
    String patronymic;
    @Column(unique = true)
    Long phone;

}
