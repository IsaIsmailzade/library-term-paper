package library.service;

import library.dao.UserDao;
import library.dto.CreateUserDto;
import library.dto.UserDto;
import library.entity.Users;
import library.exception.ValidationException;
import library.mapper.CreateUserMapper;
import library.mapper.UserMapper;
import library.validator.CreateUserValidator;
import library.validator.ValidationResult;
import lombok.SneakyThrows;

import java.util.Optional;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    UserDao userDao = UserDao.getInstance();
    UserMapper usersEntityMapper = UserMapper.getInstance();
    CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    CreateUserValidator createUserValidator = CreateUserValidator.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(usersEntityMapper::mapFrom);
    }

    @SneakyThrows
    public void create(CreateUserDto createUserDto) {
        ValidationResult valid = createUserValidator.isValid(createUserDto);
        if (!valid.isValid()) {
            throw new ValidationException(valid.getErrors());
        }
        Users users = createUserMapper.mapFrom(createUserDto);
        userDao.save(users);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
