package com.headerits.mapper;

import com.headerits.common.BaseMapperTest;
import com.headerits.entity.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p> Description: 系统用户测试类 </p>
 * <p> Title: SysUserMapperTest </p>
 * <p> Create Time: 2018/10/30 17:22 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class SysUserMapperTest extends BaseMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {

        SysUser sysUser = new SysUser();
        //sysUser.setUserName("张三");
        //sysUser.setPassword();
    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void selectByExample() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void updateByExampleSelective() throws Exception {

    }

    @Test
    public void updateByExample() throws Exception {

    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

}