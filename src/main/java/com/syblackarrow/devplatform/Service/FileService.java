package com.syblackarrow.devplatform.Service;

import com.syblackarrow.devplatform.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Autowired
    UserService userService ;

    /**
     * 文件上传服务器
     * @param file 上传的文件
     */
    public void fileUpload(MultipartFile file){
        User user = userService.getCurrentUser() ;
        user.getId() ;
    }
}
