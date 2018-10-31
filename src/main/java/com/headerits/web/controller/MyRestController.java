package com.headerits.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 * <p>Title: MyRestController </p>
 * <p>Create Time: 2018/9/5 11:53 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@RestController
@RequestMapping("myRest")
public class MyRestController {

    @RequestMapping("doGet")
    public String doGet() {
        return "doGet";
    }

    @RequestMapping("doGet1")
    public String doGet1(String username) {
        return "doGet1" + username;
    }

    @RequestMapping("doGet2")
    public User doGet2(User user) {
        return user;
    }

    class User {
        private String username;

        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
