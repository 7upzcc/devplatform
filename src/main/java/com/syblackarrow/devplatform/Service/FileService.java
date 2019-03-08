package com.syblackarrow.devplatform.Service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.syblackarrow.devplatform.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class FileService {

    @Autowired
    UserService userService ;

    /**
     * 文件上传服务器
     * @param file 上传的文件
     */
    public void fileUpload(MultipartFile file){
        JSONObject userInfo = JSONUtil.parseObj(userService.getCurrentUser()) ;
        String userId = userInfo.getStr("id") ;
        System.out.println("fileUpload >>>> "+userId);
    }
}
