package library.validator;

import library.dao.UserDao;
import library.dto.CreateUserDto;

import java.lang.Error;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private static final UserDao userDao = UserDao.getInstance();
    private static final String PHONE_PATTERN = "^\\+?\\d{11,12}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();

        if (!object.getPhone().matches(PHONE_PATTERN)) {
            validationResult.add(java.lang.Error.of("invalid.phone", "Phone is invalid"));
        }

        if (object.getPassword().length() < 8) {
            validationResult.add(java.lang.Error.of("invalid.password", "Password is too short"));
        }

        if (userDao.findByPhone(object.getPhone()).isPresent()) {
            validationResult.add(java.lang.Error.of("invalid.phone", "Phone is already in use"));
        }

        if (userDao.findByEmail(object.getEmail()).isPresent()) {
            validationResult.add(java.lang.Error.of("invalid.email", "Email is already in use"));
        }

        if (!object.getEmail().matches(EMAIL_PATTERN)) {
            validationResult.add(Error.of("invalid.email", "Email is invalid"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
