package com.msp.springmvc.controlleradvice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhao on 2017/2/16.
 *
 *  通过@ControllerAdvice,我们可以将对于控制器的全局配置放置在同一个位置,注解了@Controller的类的方法
 *  可使用@ExceptionHandler、@InitBinder、@ModelAttribute注解到方法上,这对所有注解了@RequestMapping的
 *  控制器内的方法有效
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)//用于全局处理控制器的异常,通过value属性可过滤拦截的条件,拦截所有Exception
    public ModelAndView exception(Exception exception, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }

    /*
        此处使用@ModelAttribute注解将键值对添加到全局,所有注解@RequestMapping的方法均可获得此键值对
     */
    @ModelAttribute
    public void addAttributrs(Model model){
        model.addAttribute("msg","额外信息");
    }

    @InitBinder//用来设置WebDataBinder,WebDataBinder用来自动绑定前台请求参数到Model中
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");//演示忽略request参数的id
    }
}
