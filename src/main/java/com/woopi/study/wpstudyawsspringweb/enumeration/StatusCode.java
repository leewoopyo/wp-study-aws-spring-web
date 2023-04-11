package com.woopi.study.wpstudyawsspringweb.enumeration;

public enum StatusCode {
    /*성공*/
    SUCCESS("0000", null),

    /*공통 메시지*/
    PARAMETER_NOT_FOUND("1001", "필수 파라메터가 없습니다."),

    /*유저 관련 메시지*/
    USER_INFO_NOT_FOUND("2001", "유저 정보를 찾을 수 없습니다."),

    /*알수 없는 오류*/
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
