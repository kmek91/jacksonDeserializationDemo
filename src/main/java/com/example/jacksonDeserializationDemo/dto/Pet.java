package com.example.jacksonDeserializationDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    private int age;

    private String name;

    private String favoriteColor;

}
