package com.springtx.springtx.order;

/**
 * 비즈니스 예외는 CheckException 으로 만듦
 */
public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
