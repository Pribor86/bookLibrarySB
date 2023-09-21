package com.example.booklibrary.repository;

import com.example.booklibrary.model.BookReportEntity;
import com.example.booklibrary.model.dao.BookReportRequest;
import com.example.booklibrary.model.dao.BookReportResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookReportRepository {
    private static final RowMapper<BookReportEntity> BOOK_ROW_MAPPER =
            (rs, row) -> BookReportEntity.builder()
                    .id(rs.getString("id"))
                    .title(rs.getString("title"))
                    .author(rs.getString("author"))
                    .genre(rs.getString("genre"))
                    .published(rs.getString("published"))
                    .description(rs.getString("description"))
                    .quantity(rs.getInt("quantity"))
                    .lastUpdate(rs.getString("lastupdate"))
                    .build();
    private final NamedParameterJdbcOperations jdbcOperations;
    @Override
    public void addBookReport(BookReportRequest bookReportRequest, String id) {
        jdbcOperations.update("INSERT INTO book (" +
                        "id," +
                        " title," +
                        " author," +
                        " genre," +
                        " published," +
                        " description," +
                        " quantity," +
                        " lastupdate" +
                        ") VALUES (" +
                        ":id," +
                        " :title," +
                        " :author," +
                        " :genre," +
                        " :published," +
                        " :description," +
                        " :quantity," +
                        " :lastupdate" +
                        ")",
                Map.of("id", id,
                        "title", bookReportRequest.title(),
                        "author", bookReportRequest.author(),
                        "genre", bookReportRequest.genre(),
                        "published", bookReportRequest.published(),
                        "description", bookReportRequest.description(),
                        "quantity", bookReportRequest.quantity(),
                        "lastupdate", LocalDateTime.now()));
    }
    @Override
    public void removeBookReport(String id) {
        log.info("Removing book with id: {}", id);
        try {
            jdbcOperations.update("DELETE FROM book WHERE id = :id", Map.of("id", id));
        } catch (Exception e) {
            log.error("Error while removing book by id = " + id, e);
        }
    }

    @Override
    public BookReportResponse updateBookReport(BookReportRequest bookReportRequest, String id) {
        jdbcOperations.update("UPDATE book SET " +
                        "title = :title," +
                        " author = :author," +
                        " genre = :genre," +
                        " published = :published," +
                        " description = :description," +
                        " quantity = :quantity," +
                        " lastupdate = :lastupdate" +
                        " WHERE" +
                        " id = :id",
                Map.of("id", id,
                        "title", bookReportRequest.title(),
                        "author", bookReportRequest.author(),
                        "genre", bookReportRequest.genre(),
                        "published", bookReportRequest.published(),
                        "description", bookReportRequest.description(),
                        "quantity", bookReportRequest.quantity(),
                        "lastupdate", LocalDateTime.now()));
        return new BookReportResponse("Book updated", true);
    }
    @Override
    public List<BookReportEntity> getAllBookReports() {
        return jdbcOperations.query(
                        "SELECT * FROM book", BOOK_ROW_MAPPER)
                .stream()
                .toList();
    }
    @Override
    public Optional<BookReportEntity> getBookReport(String name) {
        return jdbcOperations.query(
                        "SELECT * FROM book WHERE title LIKE :title",
                        Map.of(
                                "title",
                                '%' + name + '%'
                        ),
                        BOOK_ROW_MAPPER)
                .stream()
                .findFirst();
    }
    @Override
    public void deleteAll() {
        jdbcOperations.update("DELETE FROM book", Map.of());
    }
}
