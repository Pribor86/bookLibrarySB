package com.example.booklibrary.service;

import com.example.booklibrary.model.BookReportEntity;
import com.example.booklibrary.model.dao.BookReportRequest;
import com.example.booklibrary.model.dao.BookReportResponse;

import java.util.List;
import java.util.Optional;

public interface BookReportService {
    BookReportResponse addBookReport(BookReportRequest bookRequest);
    BookReportResponse removeBookReport(String id);
    BookReportResponse updateBookReport(BookReportRequest bookRequest, String id);
    List<BookReportEntity> getAllBookReports();
    Optional<BookReportEntity> getBookReport(String name);
}
