package com.msp.springmvc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by zhao on 2017/2/17.
 */
@Service
public class PushService {

    private DeferredResult<String> deferredResult;

    //在PushService里产生DeferredResult给控制器使用,通过@Scheduled注解的方法定时更新DeferredResult
    public DeferredResult<String> getAsyncUpdate(){
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh(){
        if (deferredResult != null){
            deferredResult.setResult(System.currentTimeMillis()+"");
        }
    }
}
