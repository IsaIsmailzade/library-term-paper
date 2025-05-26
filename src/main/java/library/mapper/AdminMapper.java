package library.mapper;

import library.dto.AdminDto;
import library.entity.Admins;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminMapper implements Mapper<Admins, AdminDto> {

    @Getter
    private static final AdminMapper INSTANCE = new AdminMapper();

    @Override
    public AdminDto mapFrom(Admins admins) {
        return AdminDto.builder()
                .email(admins.getEmail())
                .build();
    }
}
