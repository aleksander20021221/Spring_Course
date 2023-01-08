package com.spring3.zoo.impl;

import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Component
@Setter
@Getter
public class Cat implements Animal {
    private Food food;
    private Integer age = 3;

    @Override
    public void voice() {
        System.out.println("mi");
    }

    @Override
    public void feed(Food food) {
        this.food=food;
    }

    @Override
    public void throwException() {
        throw new RuntimeException ("aaaaaaaaaa");
    }
}
