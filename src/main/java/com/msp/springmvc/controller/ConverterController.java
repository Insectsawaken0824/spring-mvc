package com.msp.springmvc.controller;

import com.msp.springmvc.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhao on 2017/2/16.
 */
@Controller
public class ConverterController {
    @RequestMapping(value = "/convert", produces = {"application/x-wisely"})//自定义的媒体类型
    public @ResponseBody DemoObj convert(@RequestBody DemoObj obj){
        return obj;
    }
}
