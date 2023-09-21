package com.example.booklibrary.web;

import com.example.booklibrary.model.BookReportEntity;
import com.example.booklibrary.model.dao.BookReportResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.booklibrary.model.dao.BookReportRequest;
import com.example.booklibrary.service.BookReportService;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private BookReportService bookService;

    @Autowired
    private MockMvc mockMvc;

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
    public void addBookTest() throws Exception {
        BookReportRequest bookRequest = new BookReportRequest(
                "Book1",
                "Author1",
                "Genre1",
                "2001",
                "Description1",
                1
        );

        BookReportResponse bookResponse = new BookReportResponse("Book added successfully", true);
        when(bookService.addBookReport(bookRequest)).thenReturn(bookResponse);

        String bookRequestJson = mapper.writeValueAsString(bookRequest);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/books/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((bookRequestJson)))
                .andExpect(status().isOk())
                .andReturn();

        BookReportResponse result = mapper.readValue(mvcResult.getResponse().getContentAsString(), BookReportResponse.class);

        assertThat(result).isEqualTo(bookResponse);
    }


    @Test
    public void removeBookTest() throws Exception {
        String id = UUID.randomUUID().toString();
        BookReportResponse bookResponse = new BookReportResponse("Book removed", true);
        when(bookService.removeBookReport(id)).thenReturn(bookResponse);

        String bookRequestJson = mapper.writeValueAsString(id);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/books/remove/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((bookRequestJson)))
                .andExpect(status().isOk())
                .andReturn();

        BookReportResponse result = mapper.readValue(mvcResult.getResponse().getContentAsString(), BookReportResponse.class);

        assertThat(result).isEqualTo(bookResponse);
    }

    @Test
    public void updateBookTest() throws Exception {
        String id = UUID.randomUUID().toString();
        BookReportRequest bookRequest = new BookReportRequest(
                "Book1",
                "Author1",
                "Genre1",
                "2001",
                "Description1",
                1
        );

        BookReportResponse bookResponse = new BookReportResponse("Book updated successfully", true);
        when(bookService.updateBookReport(bookRequest, id)).thenReturn(bookResponse);

        String bookRequestJson = mapper.writeValueAsString(bookRequest);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/books/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((bookRequestJson)))
                .andExpect(status().isOk())
                .andReturn();

        BookReportResponse result = mapper.readValue(mvcResult.getResponse().getContentAsString(), BookReportResponse.class);

        assertThat(result).isEqualTo(bookResponse);

    }

    @Test
    public void getBooksTest() throws Exception {
        List<BookReportEntity> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        when(bookService.getAllBookReports()).thenReturn(bookList);

        MvcResult result = mockMvc.perform(get("/api/v1/books/get-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(mapper.writeValueAsString(bookList));
    }

    @Test
    public void getBookTest() throws Exception {
        String id = UUID.randomUUID().toString();
        String name = "Book1";
        BookReportEntity book = BookReportEntity.builder().
                id(id)
                .title("Book1")
                .author("Author1")
                .genre("Genre1")
                .published("2000")
                .description("Description1")
                .quantity(1)
                .build();

        when(bookService.getBookReport(name)).thenReturn(Optional.ofNullable(book));

        MvcResult result = mockMvc.perform(get("/api/v1/books/get/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(mapper.writeValueAsString(book));
    }
}
