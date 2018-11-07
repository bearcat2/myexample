package com.headerits.web.controller;

import com.headerits.constants.SystemConstants;
import com.headerits.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p> Description: 测试spring session 功能 </p>
 * <p> Title: SpringSeesionController </p>
 * <p> Create Time: 2018/11/7 11:46 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/springSeesion/")
public class SpringSeesionController {

    @RequestMapping("login")
    public String login(HttpSession session) {
        log.info("模拟用户登录成功...");
        SysUser sysUser = new SysUser();
        sysUser.setId(1);
        sysUser.setUserName("zzp");
        sysUser.setPassword("123456");
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setEnable((byte) 1);
        session.setAttribute(SystemConstants.CURRENT_USER, sysUser);
        return "用户登录成功";
    }

    @GetMapping("getUserInfo")
    public SysUser getUserInfo(HttpSession session) {
        SysUser sysUser = (SysUser) session.getAttribute(SystemConstants.CURRENT_USER);
        return sysUser;
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SystemConstants.CURRENT_USER);
        return "用户注销成功";
    }


}
