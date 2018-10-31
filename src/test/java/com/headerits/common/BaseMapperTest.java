package com.headerits.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> Description: 通用mapper测试类 </p>
 * <p> Title: BaseMapperTest </p>
 * <p> Create Time: 2018/10/30 16:34 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-dao.xml")
@Transactional
public class BaseMapperTest {

    @Test
    public void contextLoaders() {

    }
}
