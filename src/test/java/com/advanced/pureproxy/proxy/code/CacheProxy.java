package com.advanced.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 클라이언트 -> 프록시 -> 실제 객체 순으로 참조되도록 작성
 */
@Slf4j
public class CacheProxy implements Subject {

    private Subject target; //프록시 입장에서 호출해야 되는 대상 -> 실제 객체
    private String cacheValue; //캐시 값

    public CacheProxy(Subject target) {
        this.target = target;
    }

    /**
     * 프록시 패턴의 접근제어
     * @return 실제 객체(캐시값이 없는경우), 캐시값이 존재하면 해당 캐시값을 그대로 반환
     */
    @Override
    public String operation() {
        if (cacheValue == null) {
            //cacheValue 에 값이 없으면 실제 객체를 호출
            cacheValue = target.operation();
        }
        //cacheValue 에 값이 있으면 실제 객체를 전혀 호출하지 않고 캐시 값을 그대로 반환
        log.info("프록시 호출");
        return cacheValue;
    }
}
