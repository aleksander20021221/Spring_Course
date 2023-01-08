package com.spring3;
import com.spring3.configuration.AnnotationConfiguration;
import com.spring3.configuration.BeanConfiguration;
import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalAsyncService;
import com.spring3.zoo.AnimalService;
import com.spring3.zoo.Zoo;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = getAnnotationContext("annotationConfiguration");
        Zoo setterZoo = context.getBean("zoo", Zoo.class);

//        setterZoo.getAnimals().forEach(Animal::voice);
        setterZoo.getAnimal1().voice();
        setterZoo.getAnimal2().voice();

//        setterZoo.doAllAnimalsHungry();
        System.out.println(setterZoo);

        AnimalService animalService= context.getBean("animalServiceImpl", AnimalService.class);
        Animal cat = context.getBean("cat", Animal.class);
        Food food = Food.builder()
                        .expiredDate(LocalDateTime.now().plusHours(3))
                        .foodType(FoodType.FISH)
                        .value(BigDecimal.valueOf(10)).build();

        animalService.feedAnimal(
                food,
                cat
        );

        cat.getAge();
        try {
            cat.throwException();
        } catch (Throwable ignored){
        }

        AnimalAsyncService animalAsyncService = context.getBean(AnimalAsyncService.class);
        for (int i = 0; i < 5; i++) {
            animalAsyncService.feed(cat, food);
        }

        List<CompletableFuture<Double>> results = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            results.add(animalAsyncService.getAverageAnimalsAge());
        }
        CompletableFuture.anyOf(results.toArray(new CompletableFuture[0])).join();

        System.out.println(
                "Results: " + results.stream()
                        .map(value -> {
                            try {
                                return String.valueOf(value.get());
                            } catch (InterruptedException | ExecutionException e){
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.joining(", "))
        );

    }

//    private static ApplicationContext getXmlContext() {
//        return new ClassPathXmlApplicationContext("XmlApplicationContext.xml");
//    }

    private static ApplicationContext getAnnotationContext(String profile){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.getEnvironment().setActiveProfiles(profile);
        annotationConfigApplicationContext.register(AnnotationConfiguration.class,BeanConfiguration.class);
        annotationConfigApplicationContext.refresh();
        return annotationConfigApplicationContext;
    }
}
