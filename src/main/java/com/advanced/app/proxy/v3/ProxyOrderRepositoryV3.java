package com.advanced.app.proxy.v3;

import org.springframework.stereotype.Repository;

/**
 * 컴포넌트 스캔으로 스프링 빈 자동 등록
 */
@Repository
public class ProxyOrderRepositoryV3 {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
