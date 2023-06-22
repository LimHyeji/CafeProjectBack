package com.project.cafe.exception.list;

public class MaliciousAccessExcption extends BusinessException {

    public MaliciousAccessExcption() {
        super("악의적인 접근 감지!");
    }
}
