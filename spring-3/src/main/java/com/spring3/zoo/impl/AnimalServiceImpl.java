package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.Marker;
import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalService;
import com.spring3.zoo.event.AnimalIsHungryEvent;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Marker
public class AnimalServiceImpl implements AnimalService {

    @Override
    public void feedAnimal(Food food, Animal animal) {
        animal.feed(food);
    }

    @EventListener
    public void feedByEvent(AnimalIsHungryEvent animalIsHungryEvent){
        Animal animal = animalIsHungryEvent.getAnimal();
        animal.feed(
                Food.builder()
                        .value(BigDecimal.valueOf(10))
                        .foodType(FoodType.FISH)
                        .expiredDate(LocalDateTime.now().plusMinutes(4))
                        .build());
    }
}
