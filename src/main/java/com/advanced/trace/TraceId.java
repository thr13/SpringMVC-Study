package com.advanced.trace;

import lombok.Getter;

import java.util.UUID;

/**
 * 로그 추적기는 트랜잭션 ID, Level 을 가진다
 * 트랜잭션 레벨은 0,1,2 로 나눠져있다
 */
@Getter
public class TraceId {

    private String id; //트랜잭션 아이디
    private int level; //트랜잭션 레벨


    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    // 외부에는 공개안하고 내부에서만 사용하는 생성자
    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // UUID 앞의 8자리만 사용
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

}
