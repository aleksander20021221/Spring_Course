package com.spring3.zoo.impl;

import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Component
@Setter
@Getter
public class Dog implements Animal {
    private static final List<FoodType> possibleFoodTypes = Collections.singletonList(FoodType.MEAT);
    private Integer age = 4;
    private Food food;
    @Override
    public void voice() {
        System.out.println("gav");
    }

    @Override
    public void feed(Food food) {
        this.food=food;
    }

    @Override
    public void eat() {
        food.setValue(food.getValue().subtract(BigDecimal.valueOf(2)));
    }

    @Override
    public void throwException() {

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
