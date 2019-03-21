package com.syblackarrow.devplatform.Service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.util.StringUtils;
import com.syblackarrow.devplatform.Core.ControllerReturn;
import com.syblackarrow.devplatform.Core.ServiceReturn;
import com.syblackarrow.devplatform.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public String fileUpload(MultipartFile file){
        JSONObject userInfo = JSONUtil.parseObj(userService.getCurrentUser()) ;
        String userId = userInfo.getStr("id") ;
        if(StringUtils.isEmpty(userId)){
            return null ;
        }
        File desFile = new File("/home"+File.separator+"upload"+File.separator+userId+File.separator+file.getOriginalFilename()) ;
        try {
            FileUtil.writeBytes(file.getBytes(),desFile) ;
        } catch (IOException e) {
            return ControllerReturn.FAIL("上传失败："+e.getMessage()) ;
        }
        return ControllerReturn.SUCCESS("上传成功",desFile.getAbsolutePath()) ;
    }

    /**
     * 保存上传表单，通用方法。
     * @param dataMap
     * @return
     */
    public ServiceReturn saveUpload(Map dataMap){
        String filename = (String)dataMap.get("filename") ;
        String fileurl = (String) dataMap.get("fileurl") ;
        Map<String,Object> userInfo  = userService.getCurrentUser() ;
        String userId = (String) userInfo.get("id") ;
        return ServiceReturn.SUCCESS("保存成功") ;
    }
}
