package com.woopi.study.wpstudyawsspringweb.enumeration;

public enum StatusCode {
    SUCCESS("0000", null),
    UNKNOWN_EXCEPTION("9999", "알수없는 오류가 발생했습니다. 관리자에게 문의하세요.");

    String code;

    String message;

    StatusCode(String code, String message) {
        this.code    = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
