package com.spring3.zoo;

import com.spring3.zoo.event.AnimalIsHungryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Zoo {
    private final Animal animal1;
    private final Animal animal2;
    private final List<Animal> animals;
    private final ApplicationEventPublisher eventPublisher;

    @Value(value = "${name}")
    private String name;

    @Autowired
    public Zoo(
            Animal cat,
            Animal dog, List<Animal> animals, ApplicationEventPublisher eventPublisher) {
        this.animal1 = cat;
        this.animal2 = dog;
        this.animals = animals;

        this.eventPublisher = eventPublisher;
    }

//    public void doAllAnimalsHungry(){
//        animals.forEach(animal -> eventPublisher.publishEvent( new AnimalIsHungryEvent <> (animal)));
//    }

    public Iterable<Animal> getAnimals() {
        return animals;
    }

    public Animal getAnimal1() {
        return animal1;
    }
    public Animal getAnimal2(){
        return animal2;
    }

    public String getName(){ return name; }

}
