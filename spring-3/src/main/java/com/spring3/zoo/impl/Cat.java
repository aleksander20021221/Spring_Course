package com.spring3.zoo.impl;

import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


@Component
@Setter
@Getter
public class Cat implements Animal {
    private static final List<FoodType> possibleFoodTypes = Collections.singletonList(FoodType.FISH);
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
    public void eat() {
        food.setValue(food.getValue().subtract(BigDecimal.valueOf(3)));
    }

    @Override
    public void throwException() {
        throw new RuntimeException ("aaaaaaaaaa");
    }

    @Override
    public List<FoodType> getPossibleFoodTypes() {
        return possibleFoodTypes;
    }

    @Override
    public boolean isHungry() {
        return food.getValue().compareTo(BigDecimal.ZERO) > 0;
    }
}
