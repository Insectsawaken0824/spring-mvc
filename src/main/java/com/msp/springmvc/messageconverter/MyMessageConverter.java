package com.msp.springmvc.messageconverter;

import com.msp.springmvc.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by zhao on 2017/2/16.
 *  继承AbstractHttpMessageConverter接口来实现自定义的HttpMessageConverter
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    public MyMessageConverter() {
        //新建一个自定义媒体类型application/x-wisely
        super(new MediaType("application","x-wisely", Charset.forName("UTF-8")));
    }

    /*
        表明本HttpMessageConverter只处理DemoObj这个类
     */
    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /*
        处理请求的数据
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(),Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObj(Long.parseLong(tempArr[0]),tempArr[1]);
    }

    /*
        处理如何输出数据到response
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:"+obj.getId()+"-"+obj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
