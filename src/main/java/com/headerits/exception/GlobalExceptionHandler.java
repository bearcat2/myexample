package com.headerits.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

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
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        log.info("系统发生异常了", e);
        return null;
    }
}
