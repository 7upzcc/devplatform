package com.syblackarrow.devplatform.ApiController;

import cn.hutool.core.io.FileUtil;
import com.syblackarrow.devplatform.Core.ControllerReturn;
import com.syblackarrow.devplatform.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    FileService fileService ;
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        return fileService.fileUpload(file);
    }
}
