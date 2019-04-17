package com.syblackarrow.devplatform.Dao;

import com.syblackarrow.devplatform.Model.FileUpload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FileDao {
    public void saveUpload(FileUpload fileUpload) ;
    public List<FileUpload> getCurrentUserFiles(String userId , Integer start , Integer end) ;
    public FileUpload getFileUpload(String id) ;
    public void delFile(String id , String userId) ;
    public Integer getFileOrder(String id , String userId) ;
}
