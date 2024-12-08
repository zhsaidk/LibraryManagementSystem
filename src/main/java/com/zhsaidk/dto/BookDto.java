package com.zhsaidk.dto;

import com.zhsaidk.database.Entity.Author;
import lombok.Value;

@Value
public class BookDto {
    Long id;
    String title;
    Integer year;
    Author author;
}
