package library.mapper;

import library.dto.CreateUserDto;
import library.entity.Users;

public class CreateUserMapper implements Mapper<CreateUserDto, Users> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public Users mapFrom(CreateUserDto createUserDto) {
        return Users.builder()
                .name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .phone(createUserDto.getPhone())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
