package com.example.jacksonDeserializationDemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value={ "age", "favoriteColor" }, allowGetters=true)
public class PetWithIgnoredFields {

    private int age;

    private String name;

    private String favoriteColor;

}
