package com.spring3.zoo.jobs;

import com.spring3.zoo.Animal;
import com.spring3.zoo.event.AnimalIsHungryEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EatJob {

    private final List<Animal> animals;
    private final ApplicationEventPublisher eventPublisher;

    public EatJob(List<Animal> animals, ApplicationEventPublisher eventPublisher) {
        this.animals = animals;
        this.eventPublisher = eventPublisher;
    }


    @Scheduled(cron = "1 * * * * *")
    public void eatJob(){
        animals.forEach(animal -> {
            animal.eat();
            if (animal.isHungry()){
                eventPublisher.publishEvent(new AnimalIsHungryEvent<>(animal));
            }
        });
    }
}
