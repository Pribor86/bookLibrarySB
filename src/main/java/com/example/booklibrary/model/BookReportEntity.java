package com.example.booklibrary.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
@Data
public class BookReportEntity {
    String id;
    String title;
    String author;
    String genre;
    String published;
    String description;
    int quantity;
    String lastUpdate;
}
