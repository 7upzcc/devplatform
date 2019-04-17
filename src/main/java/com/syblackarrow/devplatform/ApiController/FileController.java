package com.syblackarrow.devplatform.ApiController;

import cn.hutool.core.io.FileUtil;
import com.syblackarrow.devplatform.Core.ControllerReturn;
import com.syblackarrow.devplatform.Core.ServiceReturn;
import com.syblackarrow.devplatform.Core.ServiceReturnCode;
import com.syblackarrow.devplatform.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileController extends BaseController {

    @Autowired
    FileService fileService;

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println("==================开始上传文件=====================");
        return fileService.fileUpload(file);
    }

    @RequestMapping("/submit")
    public String submit(@RequestParam("dataJson")String dataJson) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+dataJson);
        ServiceReturn sr = fileService.saveUpload(dataJson);
        if (sr.getServiceReturnCode() == ServiceReturnCode.SUCCESS) {
            return ControllerReturn.SUCCESS("保存成功");
        } else {
            return ControllerReturn.FAIL(sr.getExtendsMessage());
        }

    }
    @RequestMapping("/getFiles")
    public String getFiles(@RequestParam("page")Integer page){
        ServiceReturn sr = fileService.getFiles(page) ;
        if(sr.getServiceReturnCode()==ServiceReturnCode.SUCCESS){
            return ControllerReturn.SUCCESS("查询成功",sr.getData()) ;
        }else{
            return ControllerReturn.FAIL("查询失败") ;
        }
    }

    @RequestMapping("/download")
    @ResponseBody
    public void download(@RequestParam("id") String id , HttpServletResponse response){
        Map<String,Object> downloadInfo = fileService.getFile(id) ;
        File downloadFile = (File)downloadInfo.get("file") ;
        String filename = (String)downloadInfo.get("filename") ;
        byte[] buffer = FileUtil.readBytes(downloadFile) ;
        try {
        if (null != buffer && buffer.length > 0) {
            response.reset();
            response.addHeader("Access-Control-Allow-Origin","*");
            response.addHeader("Access-Control-Allow-Methods","GET");
            response.addHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
            response.addHeader("Content-Disposition","attachment;filename="+ filename);
            response.addHeader("Content-Length", "" + buffer.length);
            //response.setContentType("application/octet-stream");
            response.setContentType("image/png;charset=utf-8");
            OutputStream stream = response.getOutputStream();
            stream.write(buffer);
            stream.flush();
            stream.close();
        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("delFile")
    @ResponseBody
    public String delFile(@RequestParam("id") String id){
        ServiceReturn sr = fileService.delFiles(id) ;
        if(sr.getServiceReturnCode().equals(ServiceReturnCode.SUCCESS)){
            return ControllerReturn.SUCCESS("删除成功") ;
        }else{
            return ControllerReturn.FAIL(sr.getExtendsMessage()) ;
        }
    }
}
