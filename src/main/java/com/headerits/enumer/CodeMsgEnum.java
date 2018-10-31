package com.headerits.enumer;

/**
 * <p> Description: 系统错误码枚举对象 </p>
 * <p> Title: CodeMsgEnum </p>
 * <p> Create Time: 2018/10/31 21:13 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public enum CodeMsgEnum {
    SUCCESS(0, "success"),
    VAIDATOR_ERROR(1, "参数校验错误"),
    SERVER_ERROR(500, "服务器内部错误");

    private int code;

    private String msg;

    CodeMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
