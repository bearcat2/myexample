package com.headerits.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * <p>Description: </p>
 * <p>Title: DemoController </p>
 * <p>Create Time: 2018/8/8 14:35 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Controller
public class DemoController {

    @Autowired
    private ApplicationContext applicationContext;


    @RequestMapping(value = "/demo1")
    public String demo1(HttpSession session) {
        String random = UUID.randomUUID().toString();
        session.setAttribute("randomxx", random);
        return "repeat";
    }

    @RequestMapping("/demo2")
    @ResponseBody
    public String demo2(HttpSession session, String random) {
        String serverRandom = (String) session.getAttribute("randomxx");
        if (!random.equals(serverRandom)) {
            System.out.println("已提交");
        }
        session.removeAttribute("randomxx");
        return "repeat";
    }

    @RequestMapping(value = "/demo3")
    @ResponseBody
    public String demo3(HttpSession session) {
        session.invalidate();
        return "repeat";
    }

}
