package library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Users {

    Long id;
    String name;
    String surname;
    String email;
    String password;
    String phone;
}
