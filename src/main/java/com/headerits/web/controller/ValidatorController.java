package com.headerits.web.controller;

import com.alibaba.fastjson.JSON;
import com.headerits.common.Result;
import com.headerits.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> Description: 测试springmvc校验器功能的控制器 </p>
 * <p> Title: ValidatorController </p>
 * <p> Create Time: 2018/10/31 21:46 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/validator")
public class ValidatorController {

    @RequestMapping("/addSysUser")
    public Result addSysUser(@Validated SysUser sysUser ,BindingResult bindingResult) throws Exception {
        Map<String, String> errorMap = new LinkedHashMap<>();
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
        log.info("错误消息: {}", JSON.toJSONString(errorMap));
        return Result.success();
    }


}
