package com.example.demo3.controller;

import com.example.demo3.dto.PersonDto;
import com.example.demo3.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable("id") String id){
        return personService.findPersonById(id);
    }
}
