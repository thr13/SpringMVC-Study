package com.advanced.pureproxy.proxy;

import com.advanced.pureproxy.proxy.code.CacheProxy;
import com.advanced.pureproxy.proxy.code.ProxyPatternClient;
import com.advanced.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject(); //실제 객체
        CacheProxy cacheProxy = new CacheProxy(realSubject); //프록시 객체
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy); //클라이언트 객체에 프록시 객체 주입시, 프록시를 통해 실제 객체가 호출된다
        client.execute();
        client.execute();
        client.execute();
    }
}
