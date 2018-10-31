package com.headerits.java.demo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * <p>Description: </p>
 * <p>Title: ExcelWriteModel </p>
 * <p>Create Time: 2018/10/10 11:58 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class ExcelWriteModel extends BaseRowModel{

    @ExcelProperty(value = "用户名", index = 0)
    private String username;

    @ExcelProperty(value = "密码", index = 1)
    private String password;

    public ExcelWriteModel() {
    }

    public ExcelWriteModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
