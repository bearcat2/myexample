package com.headerits.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p> Description: 测试spring session 功能 </p>
 * <p> Title: SpringSeesionController </p>
 * <p> Create Time: 2018/11/7 11:46 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@RestController
@RequestMapping("/springSeesion/")
public class SpringSeesionController {

    @RequestMapping("demo")
    public String demo1(HttpSession session) {
        session.setAttribute("spring seesion key", "spring seesion value");
        return "成功";
    }

    @RequestMapping("demo2")
    public String demo2(HttpSession session) {
        session.removeAttribute("spring seesion key");
        return "成功";
    }
}
