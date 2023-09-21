package com.example.booklibrary.model.dao;

public record BookReportRequest(
        String title,
        String author,
        String genre,
        String published,
        String description,
        int quantity
) {}
