package com.advanced.pureproxy.concreteproxy;

import com.advanced.pureproxy.concreteproxy.code.ConcreteClient;
import com.advanced.pureproxy.concreteproxy.code.ConcreteLogic;
import com.advanced.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy); //클라이언트 생성자에 프록시 객체 주입
        client.execute();
    }
}
