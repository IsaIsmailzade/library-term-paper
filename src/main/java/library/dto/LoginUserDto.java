package library.dto;

import lombok.Value;

@Value
public class LoginUserDto {
    String email;
    String password;
}
