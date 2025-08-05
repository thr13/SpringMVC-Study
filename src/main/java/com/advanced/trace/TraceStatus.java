package com.advanced.trace;

import lombok.Getter;

/**
 * 로그 상태 정보: 어떤 로그가 시작할때 어떠한 상태 정보를 가지고 있는지 나타낸다 그리고 로그 상태 정보는 로그를 종료할 떄 사용된다
 * 로그는 시작시간과 종료 시간을 나타내야한다
 */
@Getter
public class TraceStatus {

    private TraceId traceId; //트랜잭션 고유번호
    private Long startTimeMs; //로그 시작시간
    private String message; //로그 메시지

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }
}
