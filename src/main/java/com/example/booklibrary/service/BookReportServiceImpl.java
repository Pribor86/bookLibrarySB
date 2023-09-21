package com.example.booklibrary.service;

import com.example.booklibrary.model.BookReportEntity;
import com.example.booklibrary.model.dao.BookReportRequest;
import com.example.booklibrary.model.dao.BookReportResponse;
import com.example.booklibrary.repository.BookReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookReportServiceImpl implements BookReportService {
    private final BookReportRepository bookReportRepository;
    @Override
    public BookReportResponse addBookReport(BookReportRequest bookReportRequest) {
        String id = UUID.randomUUID().toString();
        try{
            bookReportRepository.addBookReport(bookReportRequest, id);
            return new BookReportResponse("Book added successfully", true);
        } catch (Exception e) {
            log.error("BookReport service fail for request {} {}", bookReportRequest, e.getMessage());
            return new BookReportResponse("Book add failed", false);
        }
    }
    @Override
    public BookReportResponse removeBookReport(String id) {
        try {
            bookReportRepository.removeBookReport(id);
            return new BookReportResponse("Book removed successfully", true);
        } catch (Exception e) {
            log.error("BookReportService fail for request {} {}", id, e.getMessage());
            return new BookReportResponse("Book remove failed", false);
        }
    }
    @Override
    public BookReportResponse updateBookReport(BookReportRequest bookRequest, String id) {
        try {
            bookReportRepository.updateBookReport(bookRequest, id);
            return new BookReportResponse("Book updated successfully", true);
        } catch (Exception e) {
            log.error("BookService fail for request {} {}", bookRequest, e.getMessage());
            return new BookReportResponse("Book update failed", false);
        }
    }
    @Override
    public List<BookReportEntity> getAllBookReports() {
        try {
            return bookReportRepository.getAllBookReports();
        } catch (Exception e) {
            log.error("BookService fail for request {}", e.getMessage());
            return Collections.emptyList();
        }
    }
    @Override
    public Optional<BookReportEntity> getBookReport(String name) {
        try {
            return bookReportRepository.getBookReport(name);
        } catch (Exception e) {
            log.error("BookService fail for request {} {}", name, e.getMessage());
            return Optional.empty();
        }
    }
}
