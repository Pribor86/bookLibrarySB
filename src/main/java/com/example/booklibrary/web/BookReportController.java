package com.example.booklibrary.web;

import com.example.booklibrary.model.BookReportEntity;
import com.example.booklibrary.model.dao.BookReportRequest;
import com.example.booklibrary.model.dao.BookReportResponse;
import com.example.booklibrary.service.BookReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-reports")
public class BookReportController {
    private final BookReportService bookReportService;
    @PostMapping("/add")
    BookReportResponse addBookReport(@RequestBody BookReportRequest bookReportRequest) {
        return bookReportService.addBookReport(bookReportRequest);
    }
    @PostMapping("/update/{id}")
    BookReportResponse updateBookReport(@RequestBody BookReportRequest bookReportRequest, @PathVariable String id) {
        return bookReportService.updateBookReport(bookReportRequest, id);
    }
    @PostMapping("/remove/{id}")
    BookReportResponse removeBookReport(@PathVariable String id) {
        bookReportService.removeBookReport(id);
        return new BookReportResponse("Book removed", true);
    }
    @GetMapping("/get-all")
    List<BookReportEntity> getBookReports() {
        //TODO pagination
        List<BookReportEntity> bookReportEntities = bookReportService.getAllBookReports();
        return bookReportEntities;
    }
    @GetMapping("/get/{name}")
    Optional<BookReportEntity> getBookReport(@PathVariable String name) {
        return bookReportService.getBookReport(name);
    }
}
