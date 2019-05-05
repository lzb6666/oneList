package com.whu.onelist.util;

public class ResultMsg {
    public static class Type{
        public static final int SUCCESS=1;
        public static final int FAIL=2;
    }
    private int code;
    private String message;

    public ResultMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
