package com.headerits.common;

import com.headerits.enumer.CodeMsgEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * <p> Description: 客户端统一返回结果 </p>
 * <p> Title: Result </p>
 * <p> Create Time: 2018/10/31 21:10 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Data
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
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(CodeMsgEnum codeMsgEnum) {
        return new Result(codeMsgEnum);
    }
}
