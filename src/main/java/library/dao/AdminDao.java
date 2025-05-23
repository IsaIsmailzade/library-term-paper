package library.dao;

import library.entity.Admins;
import library.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminDao implements Dao<Long, Admins> {

    @Getter
    private static final AdminDao INSTANCE = new AdminDao();
    private static final String FIND_ALL = "SELECT * FROM admins";
    private static final String FIND_BY_EMAIL_AND_PASSWORD = """
            SELECT * FROM admins
            WHERE email = ? AND password = ?
            """;

    @Override
    @SneakyThrows
    public List<Admins> findAll() {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Admins> adminsList = new ArrayList<>();
            while (resultSet.next()) {
                adminsList.add(buildAdmin(resultSet));
            }
            return adminsList;
        }
    }

    @SneakyThrows
    public Optional<Admins> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            Admins admins = null;
            if (resultSet.next()) {
                admins = buildEntity(resultSet);
            }
            return Optional.ofNullable(admins);
        }
    }

    @SneakyThrows
    private Admins buildEntity(ResultSet resultSet) {
        return Admins.builder()
                .id(resultSet.getObject("id", Long.class))
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .build();
    }

    @SneakyThrows
    private Admins buildAdmin(ResultSet resultSet) {
        return new Admins(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("email", String.class),
                resultSet.getObject("password", String.class)
        );
    }

    @Override
    public Optional<Admins> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Admins entity) {

    }

    @Override
    public Admins save(Admins entity) {
        return null;
    }
}
