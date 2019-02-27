package com.syblackarrow.devplatform.ApiController;

import cn.hutool.core.io.FileUtil;
import com.syblackarrow.devplatform.Core.ControllerReturn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        File desFile = new File("/home/zhaocc/Public/"+file.getOriginalFilename()) ;
        try {
            FileUtil.writeBytes(file.getBytes(),desFile) ;
        } catch (IOException e) {
            return ControllerReturn.FAIL("上传失败："+e.getMessage()) ;
        }
        return ControllerReturn.SUCCESS("上传成功",desFile.getAbsolutePath()) ;
    }
}
