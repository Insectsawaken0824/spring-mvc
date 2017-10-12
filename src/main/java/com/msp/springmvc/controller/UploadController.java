package com.msp.springmvc.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhao on 2017/2/16.
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file){//MultipartFile接受上传的文件
        try {
            //快速写文件到磁盘
            FileUtils.writeByteArrayToFile(new File("E:/upload/"+file.getOriginalFilename()),file.getBytes());
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }
    }
}
