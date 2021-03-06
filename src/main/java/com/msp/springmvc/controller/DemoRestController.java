package com.msp.springmvc.controller;

import com.msp.springmvc.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhao on 2017/2/15.
 */
@RestController
@RequestMapping("/rest")
public class DemoRestController {

    @RequestMapping(value = "/getjson", produces = {"application/json;charset=UTF-8"})
    public DemoObj getJson(DemoObj obj){
        return new DemoObj(obj.getId()+1,obj.getName()+"nn");
    }

    @RequestMapping(value = "/getxml", produces = {"application/xml;charset=UTF-8"})
    public DemoObj getXml(DemoObj obj){
        return new DemoObj(obj.getId()+1,obj.getName()+"nn");
    }
}
