package com.advanced.app.proxy.v2;

/**
 * 인터페이스가 없는 부채 클래스 -> 스프링 빈 수동 등록
 */
public class ProxyOrderRepositoryV2 {

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
