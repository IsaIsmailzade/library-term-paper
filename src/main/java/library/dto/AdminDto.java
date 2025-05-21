package library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AdminDto {

    Long id;
    String email;
    String password;
}
