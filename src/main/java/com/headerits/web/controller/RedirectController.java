package com.headerits.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * <p> Description: 重定向携带参数 </p>
 * <p> Title: RedirectController </p>
 * <p> Create Time: 2018/10/26 10:45 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
@Slf4j
@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @RequestMapping(value = "/test")
    public String test(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("name", "张三");
        log.info("redirectAttributes --- {}", redirectAttributes.getFlashAttributes());
        return "redirect:test1";
    }

    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1(RedirectAttributes redirectAttributes) {
        for (Map.Entry<String, ?> stringEntry : redirectAttributes.getFlashAttributes().entrySet()) {
            System.out.println(stringEntry.getKey() + " == " + stringEntry.getValue());
        }
        return "success";
    }


}
