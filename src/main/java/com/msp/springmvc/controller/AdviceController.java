package com.msp.springmvc.controller;

import com.msp.springmvc.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhao on 2017/2/16.
 */
@Controller
public class AdviceController {

    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj){
        System.out.println(obj.toString());
        throw new IllegalArgumentException("非常抱歉,参数有误/"+"来自 @ModelAttribute"+msg);
    }
}
