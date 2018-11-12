package com.headerits.exception;

import com.headerits.common.Result;
import com.headerits.enumer.CodeMsgEnum;
import com.headerits.util.RequestParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> Description: 全局异常处理器 </p>
 * <p> Title: GlobalExceptionHandler </p>
 * <p> Create Time: 2018/10/31 22:15 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        log.info("系统发生异常了", e);

        String requestParams = RequestParamUtils.getRequestParams(httpServletRequest);
        log.info("请求参数 = {}", requestParams);

        return Result.error(CodeMsgEnum.SERVER_ERROR);
    }
}
