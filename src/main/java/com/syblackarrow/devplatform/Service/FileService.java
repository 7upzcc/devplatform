package com.syblackarrow.devplatform.Service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.util.StringUtils;
import com.syblackarrow.devplatform.Core.ControllerReturn;
import com.syblackarrow.devplatform.Core.ServiceReturn;
import com.syblackarrow.devplatform.Dao.FileDao;
import com.syblackarrow.devplatform.Model.FileUpload;
import com.syblackarrow.devplatform.Model.User;
import com.syblackarrow.devplatform.Model.UserSettings;
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
    UserService userService;

    @Autowired
    FileDao fileDao ;

    /**
     * 文件上传服务器
     *
     * @param file 上传的文件
     */
    public String fileUpload(MultipartFile file) {
        JSONObject userInfo = JSONUtil.parseObj(userService.getCurrentUser());
        String userId = userInfo.getStr("id");
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        File desFile = new File("/home" + File.separator + "upload" + File.separator + userId + File.separator + file.getOriginalFilename());
        try {
            FileUtil.writeBytes(file.getBytes(), desFile);
        } catch (IOException e) {
            return ControllerReturn.FAIL("上传失败：" + e.getMessage());
        }
        return ControllerReturn.SUCCESS("上传成功", desFile.getAbsolutePath());
    }

    /**
     * 保存上传表单，通用方法。
     *
     * @param dataJson //包含了数据的一个json字符串
     * @return
     */
    public ServiceReturn saveUpload(String dataJson) {
        //1. 验证用户是否有上传资格
        if (!userService.getUserUploadSetting()) return ServiceReturn.FAIL("你没有上传资格");
        //2. 验证用户当前目录是否超限
        if (isFileSizeOverRange()) return ServiceReturn.FAIL("你的空间已经用尽");
        //3. 数据准备
        JSONObject data = JSONUtil.parseObj(dataJson);
        String filename = data.getStr("filename");
        String fileurl = data.getStr("fileurl");
        String password = data.getStr("password") ;
        Map<String, Object> userInfo = userService.getCurrentUser();
        String userId = (String) userInfo.get("id");
        FileUpload fileUpload = new FileUpload() ;
        fileUpload.setId(UUID.randomUUID().toString(true));
        fileUpload.setFilename(filename);
        fileUpload.setFileUploadUrl(fileurl);
        fileUpload.setPassword(password);
        fileUpload.setUserId(userId);
        //4 保存数据
        fileDao.saveUpload(fileUpload);
        return ServiceReturn.SUCCESS("保存成功");
    }

    /**
     * 判断用户目录的文件大小是否超限
     *
     * @return
     */
    public boolean isFileSizeOverRange() {
        File userFilePath = new File("/home" + File.separator + "upload" + File.separator + userService.getCurrentUserId());
        if (userFilePath.exists()) {
            long filesize = FileUtil.size(userFilePath);
            System.out.println("计算要求的存储限制:"+userService.getUserFileSize() * 1024 *1024+"      当前的文件夹大小:"+filesize);
            return (userService.getUserFileSize() * 1024 * 1024 < filesize);
        } else {
            return false;
        }
    }

    /**
     * 查询当前用户的所有文件，分页返回
     * @return 返回json
     */
    public ServiceReturn getFiles(Integer page){
        Integer start = 0 ;
        Integer end = 0 ;
        Integer pageCount = 10 ;
        start = (page - 1) * pageCount ;
        end = page * pageCount - 1 ;
        List<FileUpload> fileUploads = fileDao.getCurrentUserFiles(userService.getCurrentUserId() , start , end ) ;
        return ServiceReturn.SUCCESS("查询成功",fileUploads) ;
    }
}
