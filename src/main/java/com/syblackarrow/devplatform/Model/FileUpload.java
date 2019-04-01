package com.syblackarrow.devplatform.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
public class FileUpload {
    private String id ;
    private String userId ;
    private String filename ;
    private String fileUploadUrl ;
    private String password ;
    private Date uploadDate ;
    private Date lastOpenDate ;
    private Date lastDownloadDate ;
}
