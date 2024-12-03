package com.zhsaidk.dto;

import lombok.Value;

@Value
public class BookReadDto {
    Long id;
    String title;
    Integer year;
}
