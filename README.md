## 📚 Онлайн Библиотека

Этот проект представляет собой веб-приложение для управления библиотекой, которое позволяет пользователям:

- 📝 Регистрироваться и входить в систему
- 📖 Добавлять и удалять книги
- 📥 Загружать и читать книги

Проект разработан с использованием **Java**, **PostgreSQL**, а также включает в себя клиентскую часть на **JSP** и **CSS**.

### Структура базы данных

Для нормальной работы приложения понадобятся следующие таблицы в БД:

![Database Structure](https://github.com/user-attachments/assets/3e32b5f1-84f2-41b5-b9b9-d0e6fcb8678e)

### SQL-запросы для создания таблиц

```sql
CREATE TABLE books (
    id BIGINT PRIMARY KEY,
    title VARCHAR(124),
    author VARCHAR(124),
    description TEXT,
    download_fb2 VARCHAR(255),
    download_epub VARCHAR(255),
    download_pdf VARCHAR(255),
    download_docx VARCHAR(255),
    download_mobi VARCHAR(255),
    read VARCHAR(255)
);

CREATE TABLE admins (
    id BIGINT PRIMARY KEY,
    email VARCHAR(124),
    password VARCHAR(32)
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(124),
    surname VARCHAR(124),
    email VARCHAR(124),
    password VARCHAR(32),
    phone VARCHAR(32)
);
