package com.example.booklibrary.repository;

import com.example.booklibrary.model.BookReportEntity;
import com.example.booklibrary.model.dao.BookReportRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepositoryImpl bookRepository;
    @AfterEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void addBookTest() {
        String bookName = "TestBook";
        String id = UUID.randomUUID().toString();
        BookReportRequest bookRequest = new BookReportRequest(
                bookName,
                "Author1",
                "Genre1",
                "2000",
                "Description1",
                1
        );

        bookRepository.addBookReport(bookRequest, id);

        Optional<BookReportEntity> book = bookRepository.getBookReport(bookName);

        assertThat(book).isNotNull();
    }

    @Test
    public void removeBookTest() {
        String bookName = "TestBook";
        String id = UUID.randomUUID().toString();
        BookReportRequest bookRequest = new BookReportRequest(
                bookName,
                "Author1",
                "Genre1",
                "2000",
                "Description1",
                1
        );

        bookRepository.addBookReport(bookRequest, id);
        bookRepository.removeBookReport(id);

        Optional<BookReportEntity> book = bookRepository.getBookReport(bookName);

        assertThat(book).isEmpty();
    }

    @Test
    public void updateBookTest() {
        String bookName = "TestBook";
        String published = "2222";
        String id = UUID.randomUUID().toString();
        BookReportRequest bookRequest = new BookReportRequest(
                bookName,
                "Author1",
                "Genre1",
                "2000",
                "Description1",
                1
        );

        BookReportRequest bookRequestUpdated = new BookReportRequest(
                bookName,
                "Author1",
                "Genre1",
                published,
                "Description1",
                2
        );

        bookRepository.addBookReport(bookRequest, id);
        bookRepository.updateBookReport(bookRequestUpdated, id);

        Optional<BookReportEntity> book = bookRepository.getBookReport(bookName);

        assertThat(book).isNotNull();
        assertThat(book.get().getPublished()).isEqualTo(published);
    }

    @Test
    public void getAllBooksTest() {
        String id = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        BookReportRequest bookRequest = new BookReportRequest(
                "Book1",
                "Author1",
                "Genre1",
                "2000",
                "Description1",
                1
        );

        BookReportRequest bookRequest2 = new BookReportRequest(
                "Book2",
                "Author2",
                "Genre2",
                "2002",
                "Description2",
                2
        );

        bookRepository.addBookReport(bookRequest, id);
        bookRepository.addBookReport(bookRequest2, id2);

        List<BookReportEntity> books = bookRepository.getAllBookReports();

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    public void getBookTest() {
        String bookName = "TestBook";
        String id = UUID.randomUUID().toString();
        BookReportRequest bookRequest = new BookReportRequest(
                bookName,
                "Author1",
                "Genre1",
                "2000",
                "Description1",
                1
        );

        bookRepository.addBookReport(bookRequest, id);

        Optional<BookReportEntity> book = bookRepository.getBookReport(bookName);

        assertThat(book).isNotNull();
        assertThat(book.get().getTitle()).isEqualTo(bookName);
    }

    @Test
    public void deleteAllTest() {
        String id = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        BookReportRequest bookRequest = new BookReportRequest(
                "Book1",
                "Author1",
                "Genre1",
                "2000",
                "Description1",
                1
        );

        BookReportRequest bookRequest2 = new BookReportRequest(
                "Book2",
                "Author2",
                "Genre2",
                "2002",
                "Description2",
                2
        );

        bookRepository.addBookReport(bookRequest, id);
        bookRepository.addBookReport(bookRequest2, id2);
        bookRepository.deleteAll();

        List<BookReportEntity> books = bookRepository.getAllBookReports();

        assertThat(books).isNotNull();
    }
}
