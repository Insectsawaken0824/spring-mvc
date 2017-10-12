package com.msp.springmvc.controller;

import com.msp.springmvc.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by zhao on 2017/2/17.
 */
@Controller
public class AysncController {

    @Autowired
    PushService pushService;

    @RequestMapping("/defer")
    public @ResponseBody DeferredResult<String> deferredCall(){
        return pushService.getAsyncUpdate();
    }
}
