package com.headerits.service.impl;

import com.headerits.service.DemoService;
import com.headerits.util.RequestContextHolderUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: </p>
 * <p>Title: DemoServiceImpl </p>
 * <p>Create Time: 2018/9/18 17:15 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Component
public class DemoServiceImpl implements DemoService {

    @Override
    public void getRequest() {
        HttpServletRequest request = RequestContextHolderUtils.getRequest();
        System.out.println(request.getRequestURI());
    }
}
