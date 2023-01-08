package com.spring3.zoo;

import com.spring3.zoo.food.Food;

import java.util.concurrent.CompletableFuture;

public interface AnimalAsyncService {
    void feed(Animal animal, Food food);

    CompletableFuture<Double> getAverageAnimalsAge();
}
