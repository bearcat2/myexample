package com.headerits.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.headerits.enumer.CodeMsgEnum;
import lombok.Getter;

import java.io.Serializable;

/**
 * <p> Description: 客户端统一返回结果 </p>
 * <p> Title: Result </p>
 * <p> Create Time: 2018/10/31 21:10 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class Result implements Serializable {

    private int code;

    private String msg;

    private Object data;

    private Result(Object data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsgEnum codeMsgEnum) {
        this.code = codeMsgEnum.getCode();
        this.msg = codeMsgEnum.getMsg();
        this.data = "";
    }

    public static Result success() {
        return success("");
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result error(CodeMsgEnum codeMsgEnum) {
        return new Result(codeMsgEnum);
    }

}
