package com.headerits.web.controller.demo;


import com.headerits.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @RequestMapping(value = "/demo4")
    @ResponseBody
    public String demo4() {
        System.out.println("被调用了");
        return "中文测试";
    }

    @RequestMapping(value = "/demo5")
    @ResponseBody
    public SysUser demo5() {
        SysUser sysUser = new SysUser();
        sysUser.setId(2);
        sysUser.setUserName("中文测试");
        sysUser.setCreateTime(new Date());
        return sysUser;
    }


}
