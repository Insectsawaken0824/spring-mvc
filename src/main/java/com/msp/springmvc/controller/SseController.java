package com.msp.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by zhao on 2017/2/16.
 */
@Controller
public class SseController {

    //使用输出的媒体类型为text/event-stream,这是服务器端SSE的支持,本例演示每五秒向浏览器随机推送
    @RequestMapping(value = "/push",produces = "text/event-stream")
    public @ResponseBody String push(){
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3" + r.nextInt() +"\n\n";
    }
}
