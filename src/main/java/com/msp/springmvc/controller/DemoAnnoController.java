package com.msp.springmvc.controller;

import com.msp.springmvc.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhao on 2017/2/15.
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

    @RequestMapping(produces = "text/plain;charset=UTF-8")//此方法未标注路径,因此使用类级别路径.produce可定制返回的response的媒体类型和字符集
    public @ResponseBody String index(HttpServletRequest request){//也可以接受HttpServletResponse作为参数
        return "url:"+request.getRequestURI()+" can access!";
    }

    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String demoPathVar(@PathVariable String str,HttpServletRequest request){
        return "url:"+request.getRequestURI()+" can access!"+"str:"+str;
    }

    @RequestMapping(value = "/requestParam",produces = "text/plain;charset=UTF-8")//路径/anno/requestParam?id=1
    public @ResponseBody String passRequestParam(Long id,HttpServletRequest request){
        return "url:"+request.getRequestURI()+" can access!"+"id:"+id;
    }

    @RequestMapping(value = "/obj",produces = "application/json;charset=UTF-8")//路径/anno/requestParam?id=1&name=nihao
    public @ResponseBody String passRequesObj(DemoObj obj, HttpServletRequest request){
        return "url:"+request.getRequestURI()+" can access!"+"id:"+obj.getId()+",name:"+obj.getName();
    }

    @RequestMapping(value = {"/name1","/name2"},produces = "text/plain;charset=UTF-8")
    public @ResponseBody String remove(HttpServletRequest request){
        return "url:"+request.getRequestURI()+" can access!";
    }
}
