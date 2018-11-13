package com.headerits.web.filter;


import com.headerits.util.MyRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p> Description: 用自己的包装里取代原始HttpServletRequest 解决流只能读取一次问题 </p>
 * <p> Title: HttpServletRequestReplacedFilter </p>
 * <p> Create Time: 2018/11/13 11:40 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class HttpServletRequestReplacedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        MyRequestWrapper myRequestWrapper = new MyRequestWrapper(httpServletRequest);
        filterChain.doFilter(myRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
