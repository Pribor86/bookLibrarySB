package com.example.booklibrary.service;

import com.example.booklibrary.model.BookReportEntity;
import com.example.booklibrary.model.dao.BookReportRequest;
import com.example.booklibrary.model.dao.BookReportResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookReportServiceImpl bookService;

    BookReportEntity book1 = BookReportEntity.builder().
            id("1")
            .title("Book1")
            .author("Author1")
            .genre("Genre1")
            .published("2000")
            .description("Description1")
            .quantity(1)
            .build();

    BookReportEntity book2 = BookReportEntity.builder().
            id("2")
            .title("Book2")
            .author("Author2")
            .genre("Genr2")
            .published("2002")
            .description("Description2")
            .quantity(2)
            .build();

    BookReportEntity book3 = BookReportEntity.builder().
            id("3")
            .title("Book3")
            .author("Author3")
            .genre("Genre3")
            .published("2003")
            .description("Description3")
            .quantity(3)
            .build();

    @Test
    public void addBookTest() {
        BookReportRequest bookRequest = new BookReportRequest(
                "Book1",
                "Author1",
                "Genre1",
                "2000",
                "Description1",
                1
        );

        BookReportResponse bookResponse = new BookReportResponse("Book added successfully", true);
        when(bookService.addBookReport(bookRequest)).thenReturn(bookResponse);

        BookReportResponse bookResponse1 = bookService.addBookReport(bookRequest);

        assertThat(bookResponse1).isEqualTo(bookResponse);
    }

    @Test
    public void removeBookTest() {
        BookReportResponse bookResponse = new BookReportResponse("Book removed successfully", true);
        when(bookService.removeBookReport("1")).thenReturn(bookResponse);

        BookReportResponse bookResponse1 = bookService.removeBookReport("1");

        assertThat(bookResponse1).isEqualTo(bookResponse);
    }

    @Test
    public void updateBookTest() {
        BookReportRequest bookRequest = new BookReportRequest(
                "Book1",
                "Author1",
                "Genre1",
                "2000",
                "Description1",
                1
        );
        BookReportResponse bookResponse = new BookReportResponse("Book updated successfully", true);
        when(bookService.updateBookReport(bookRequest, "1")).thenReturn(bookResponse);

        BookReportResponse bookResponse1 = bookService.updateBookReport(bookRequest, "1");

        assertThat(bookResponse1).isEqualTo(bookResponse);
    }

    @Test
    public void getAllBooksTest() {
        List<BookReportEntity> bookList = new ArrayList<>(Arrays.asList(book1, book2, book3));
        when(bookService.getAllBookReports()).thenReturn(bookList);

        List<BookReportEntity> bookList1 = bookService.getAllBookReports();

        assertThat(bookList1).isEqualTo(bookList);
    }

    @Test
    public void getBookTest() {
        when(bookService.getBookReport("Book1")).thenReturn(Optional.of(book1));

        Optional<BookReportEntity> book = bookService.getBookReport("Book1");

        assertThat(book).isNotNull();
    }
}
