package com.universal.hibernate.app.servlet.exception.handler;

@FunctionalInterface
public interface CheckedAction {

    void perform() throws Exception;
}
