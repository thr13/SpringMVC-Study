package com.advanced.advisor;

import com.advanced.common.advice.TimeAdvice;
import com.advanced.common.service.ServiceImpl;
import com.advanced.common.service.ServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AdvisorTest {

    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //DefaultPointcutAdvisor 는 일반적인 인터페이스 구현체이다 생성자에 하나의 포인트컷과 하나의 어드바이스를 필요로 한다
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }
}
