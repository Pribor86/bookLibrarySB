package com.example.booklibrary.repository;

import com.example.booklibrary.model.BookReportEntity;
import com.example.booklibrary.model.dao.BookReportRequest;
import com.example.booklibrary.model.dao.BookReportResponse;

import java.util.List;
import java.util.Optional;

public interface BookReportRepository {
    void addBookReport(BookReportRequest bookReportRequest, String id);
    void removeBookReport(String id);
    BookReportResponse updateBookReport(BookReportRequest bookReportRequest, String id);
    List<BookReportEntity> getAllBookReports();
    Optional<BookReportEntity> getBookReport(String name);
    void deleteAll();
}
