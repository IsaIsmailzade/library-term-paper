package library.service;

import library.dao.BooksDao;
import library.dto.BookDto;
import library.dto.CreateBookDto;
import library.entity.Books;
import library.exception.ValidationException;
import library.mapper.CreateBookMapper;
import library.validator.CreateBookValidator;
import library.validator.DeleteBookValidator;
import library.validator.ValidationResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class BookService {

    private static final BookService INSTANCE = new BookService();
    private final BooksDao booksDao = BooksDao.getInstance();
    CreateBookMapper createBookMapper = CreateBookMapper.getINSTANCE();
    DeleteBookValidator deleteBookValidator = DeleteBookValidator.getINSTANCE();
    CreateBookValidator createBookValidator = CreateBookValidator.getINSTANCE();

    public List<BookDto> findAll() {
        return booksDao.findAll().stream()
                .map(books -> BookDto.builder()
                        .id(books.getId())
                        .title(books.getTitle())
                        .author(books.getAuthor())
                        .description(books.getDescription())
                        .downloadFb2(books.getDownloadFb2())
                        .downloadEpub(books.getDownloadEpub())
                        .downloadPdf(books.getDownloadPdf())
                        .downloadDocx(books.getDownloadDocx())
                        .downloadMobi(books.getDownloadMobi())
                        .read(books.getRead())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<Books> findById(Long id) {
        return booksDao.findById(id);
    }

    public void create(CreateBookDto createBookDto) {
        ValidationResult validationResult = createBookValidator.isValid(createBookDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        Books books = createBookMapper.mapFrom(createBookDto);
        booksDao.save(books);
    }

    public void delete(Long id) {
        ValidationResult validationResult = deleteBookValidator.isValid(id.toString());

        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        boolean deleted = booksDao.delete(id);
        if (!deleted) {
            throw new RuntimeException("Failed to delete the book");
        }
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}
