package com.spring3.aspect;

import com.spring3.zoo.food.Food;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnimalAspect {
    @Pointcut("execution(* com.spring3.zoo.Animal.voice())")
    public void voicePoint(){
    }

    @Pointcut("execution(* com.spring3.zoo.*.*(..))")
    public void anyAnimalMethod(){
    }

    @Pointcut("@annotation(com.spring3.aspect.annotationMarker.Marker)")
    public void annotationPointcut(){
    }

    @Pointcut("@within(com.spring3.aspect.annotationMarker.Marker)")
    public void annotationWithinPointcut(){
    }

    @Pointcut("args(food)")
    public void withArg(Food food){
    }

    @Before(value = "voicePoint()")
    public void beforeVoice(JoinPoint joinPoint){
        System.out.println("Before Voice");
    }

    @Before(value = "anyAnimalMethod() && withArg(food)", argNames = "food")
    public void beforeSetFood(Food food){
        System.out.println(food.toString());
    }

    @Before(value = "annotationWithinPointcut()")
    public void beforeServiceCall(JoinPoint joinPoint){
        System.out.println("Before Voice");
    }

    @AfterReturning (value = "execution(* com.spring3.zoo.Animal.getAge())", returning = "age")
    public void afterReturningExample(Integer age){
        System.out.println(age);
    }

    @AfterThrowing (value ="execution(* com.spring3.zoo.Animal.throwException())", throwing = "e")
    public void afterThrowing(Throwable e){
        System.out.println(e.getMessage());
    }

    @Around(value = "anyAnimalMethod() && withArg(food)", argNames = "joinPoint, food")
    public Object around(ProceedingJoinPoint joinPoint, Food food) throws Throwable {
        Object result = null;
        //before
        try {
            result = joinPoint.proceed();
            //after returning
        } catch (Throwable e) {
            e.printStackTrace();
            //after throwing
            throw e;
        } finally {
            //after
        }
        return result;
    }

}
