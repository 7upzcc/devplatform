package com.syblackarrow.devplatform.Dao;

import com.syblackarrow.devplatform.Model.FileUpload;
import org.springframework.stereotype.Component;

@Component
public interface FileDao {
    public void saveUpload(FileUpload fileUpload) ;
}
