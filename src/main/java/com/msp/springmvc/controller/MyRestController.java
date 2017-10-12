package com.msp.springmvc.controller;

import com.msp.springmvc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhao on 2017/2/17.
 */
@RestController
public class MyRestController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String testRest(){
        return demoService.sayHello();
    }
}
