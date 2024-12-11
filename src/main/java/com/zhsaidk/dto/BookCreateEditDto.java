package com.zhsaidk.dto;

import lombok.Value;

@Value
public class BookCreateEditDto {
    String title;
    Integer year;
    Long author_id;
}
