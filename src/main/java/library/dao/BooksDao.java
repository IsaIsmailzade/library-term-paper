package library.dao;

import library.entity.Books;
import library.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooksDao implements Dao<Long, Books> {

    private static final BooksDao INSTANCE = new BooksDao();
    private static final String SAVE_BOOK = """
            INSERT INTO books (title, author, description, download_fb2, download_epub, download_pdf, download_docx, download_mobi, read)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String DELETE_BOOK = """
            DELETE FROM books WHERE id = ?
            """;
    private static final String FIND_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
    private static final String FIND_ALL_BOOKS = "SELECT * FROM books";

    @Override
    public List<Books> findAll() {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BOOKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Books> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private Books buildBook(ResultSet resultSet) {
        return new Books(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("title", String.class),
                resultSet.getObject("author", String.class),
                resultSet.getObject("description", String.class),
                resultSet.getObject("download_fb2", String.class),
                resultSet.getObject("download_epub", String.class),
                resultSet.getObject("download_pdf", String.class),
                resultSet.getObject("download_docx", String.class),
                resultSet.getObject("download_mobi", String.class),
                resultSet.getObject("read", String.class)
        );
    }

    @Override
    public Optional<Books> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_BOOK_BY_ID)) {
            prepareStatement.setObject(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(buildBook(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            preparedStatement.setObject(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("No book found with the given ID");
            }
            return rowsAffected > 0;
        }
    }

    @Override
    public void update(Books entity) {

    }

    @SneakyThrows
    @Override
    public Books save(Books entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_BOOK, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getTitle());
            preparedStatement.setObject(2, entity.getAuthor());
            preparedStatement.setObject(3, entity.getDescription());
            preparedStatement.setObject(4, entity.getDownloadFb2());
            preparedStatement.setObject(5, entity.getDownloadEpub());
            preparedStatement.setObject(6, entity.getDownloadPdf());
            preparedStatement.setObject(7, entity.getDownloadDocx());
            preparedStatement.setObject(8, entity.getDownloadMobi());
            preparedStatement.setObject(9, entity.getRead());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Long.class));
        }
        return entity;
    }

    public static BooksDao getInstance() {
        return INSTANCE;
    }
}
