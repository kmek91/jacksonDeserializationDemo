package com.example.jacksonDeserializationDemo.controller;

import com.example.jacksonDeserializationDemo.dto.Pet;
import com.example.jacksonDeserializationDemo.dto.PetWithIgnoredFields;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {

    @PostMapping("/pet")
    public Pet pet(@RequestBody Pet pet) {
        log.info("Regular Pet: {}", pet);
        return pet;
    }

    @PostMapping("/petIgnored")
    public PetWithIgnoredFields petIgnored(@RequestBody PetWithIgnoredFields pet) {
        log.info("Ignored Pet: {}", pet);
        return pet;
    }

}
