package com.swj.customized.handler;

import lombok.Data;

/**
 * Created by xxb on 2018/10/22.
 */
@Data
public class MyException extends Exception {
    private String code;
    private String msg;

    public MyException() {}

    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
