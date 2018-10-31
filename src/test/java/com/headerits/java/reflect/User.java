package com.headerits.java.reflect;

/**
 * <p> Description: </p>
 * <p> Title: User </p>
 * <p> Create Time: 2018/10/24 17:12 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class User {

    private String userName;

    private String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private double demo(double a, double b) {
        return a * b;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
