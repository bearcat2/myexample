package com.headerits.enumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p> Description: 系统错误码枚举对象 </p>
 * <p> Title: CodeMsgEnum </p>
 * <p> Create Time: 2018/10/31 21:13 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@AllArgsConstructor
@Getter
public enum CodeMsgEnum {
    SUCCESS(0, "success"),
    SERVER_ERROR(500, "服务器内部错误");

    private int code;

    private String msg;
}
