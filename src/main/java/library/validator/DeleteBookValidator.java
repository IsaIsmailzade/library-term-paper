package library.validator;

import library.dao.BooksDao;
import lombok.Getter;

import library.validator.Error;

public class DeleteBookValidator implements Validator<String> {

    @Getter
    private static final DeleteBookValidator INSTANCE = new DeleteBookValidator();
    private static final String ID_PATTERN = "^\\d+$";
    private static final BooksDao booksDao = BooksDao.getInstance();

    @Override
    public ValidationResult isValid(String id) {
        ValidationResult validationResult = new ValidationResult();

        if (id == null || !id.matches(ID_PATTERN)) {
            validationResult.add(Error.of("bookId.invalid", "Invalid book ID format"));
        }

        if (booksDao.findById(Long.valueOf(id)).isEmpty()) {
            validationResult.add(Error.of("bookId.notFound", "Book with the given ID was not found"));
        }

        return validationResult;
    }
}
