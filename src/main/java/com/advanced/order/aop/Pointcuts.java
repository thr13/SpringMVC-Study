package com.advanced.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Pointcut 의 접근제어자가 public 일 경우 다른 Aspect 클래스에서도 사용가능하다
 */
@Slf4j
public class Pointcuts {

    @Pointcut("execution(* com.advanced.order..*(..))")
    public void allOrder() {}

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
