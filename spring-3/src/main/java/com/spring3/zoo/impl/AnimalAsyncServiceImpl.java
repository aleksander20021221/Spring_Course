package com.spring3.zoo.impl;

import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalAsyncService;
import com.spring3.zoo.AnimalService;
import com.spring3.zoo.food.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AnimalAsyncServiceImpl implements AnimalAsyncService {

    private final List <Animal> animals;
    private final AnimalService animalService;

    @Autowired
    public AnimalAsyncServiceImpl(List<Animal> animals, AnimalService animalService){
        this.animals= animals;
        this.animalService =animalService;
    }

    @Async("taskExecutor")
    @Override
    public void feed(Animal animal, Food food) {
        System.out.println("Feed thread: "+ Thread.currentThread().getName());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        animalService.feedAnimal(food,animal);
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Double> getAverageAnimalsAge() {
        System.out.println("Average age thread: "+ Thread.currentThread().getName());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(
                animals.stream()
                        .mapToInt(Animal::getAge)
                        .average()
                        .orElse(0)
        );
    }
}
