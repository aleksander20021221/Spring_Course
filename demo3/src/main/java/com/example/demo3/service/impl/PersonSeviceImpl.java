package com.example.demo3.service.impl;

import com.example.demo3.dto.PersonDto;
import com.example.demo3.service.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonSeviceImpl implements PersonService {
    @Override
    public PersonDto findPersonById(String id) {
        return PersonDto.builder().id(id).build();
    }
}
