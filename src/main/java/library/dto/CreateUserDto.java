package library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {

    String name;
    String surname;
    String email;
    String password;
    String phone;
}
