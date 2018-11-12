package com.headerits.web.controller.demo;

import com.headerits.scheduled.DemoScheduled;
import com.headerits.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

/**
 * <p>Description: </p>
 * <p>Title: TestController </p>
 * <p>Create Time: 2018/9/18 11:29 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DemoService demoService;

    @RequestMapping("/test")
    public String demo(HttpServletRequest request, String username) {
        System.out.println(request.getServerName());
        System.out.println(request.getServerPort());
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        System.out.println(request.getMethod());
        System.out.println(request.getQueryString());
        System.out.println(request.getAuthType());
        System.out.println(request.getScheme());
        System.out.println(request.getParameterMap());
        System.out.println(request.getPathInfo());
        System.out.println(request.getUserPrincipal());
        System.out.println(request.getCharacterEncoding());
        System.out.println(request.getContentType());
        System.out.println(request.getRemoteUser());
        System.out.println(request.getRemoteHost());
        return "success";
    }

    @RequestMapping("/test1")
    public String demo1(HttpServletRequest request, HttpSession session) {
        try {
            String realPath = WebUtils.getRealPath(session.getServletContext(), "/static/css");
            System.out.println(realPath);
            System.out.println(WebUtils.getTempDir(session.getServletContext()));
            System.out.println(WebUtils.getSessionMutex(session));
            System.out.println(WebUtils.getSessionId(request));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("/test2")
    public String demo2(HttpServletRequest request, HttpSession session) {
        LOGGER.info(
                String.format("【log4j demo】")
        );
        System.out.println("sssss");
        return "success";
    }

    @RequestMapping("/test3")
    public String demo3() {
        this.demoService.getRequest();
        return "success";
    }

    @RequestMapping("/test4")
    public String demo4(int count) {
        String cornExpression = null;
        switch (count) {
            case 1:
                // 改变成每隔5秒执行
                cornExpression = "0/5 * * * * ?";
                break;

            case 2:
                // 改成过去某个时间,相当于暂停
                cornExpression = "0/5 * * * * ? 2066";
                break;

            case 3:
                // 改成默认,每隔2秒运行
                cornExpression = "0/2 * * * * ?";
                break;

            default:
                break;
        }
        DemoScheduled.setCronExpression(cornExpression);
        return "success";
    }


}
