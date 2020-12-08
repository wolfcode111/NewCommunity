package com.nowcoder.community.community.acpect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {

    //不管返回值，所有的组件，所有的方法，所有的参数都使用下面
    @Pointcut("execution(* com.nowcoder.community.community.service.*.*(..))")
    public void pointcut(){

    }

    //在此之前执行
    @Before("pointcut()")
    public void before(){
        System.out.println("before");
    }

    //在此之后执行
    @After("pointcut()")
    public void After(){
        System.out.println("After");
    }

    //有了返回值后执行
    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("afterReturning");
    }

    //抛出异常后执行
    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("around before");
        Object obj = joinPoint.proceed();
        System.out.println("around after");
        return obj;
    }
}
