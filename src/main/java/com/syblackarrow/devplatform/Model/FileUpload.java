package com.syblackarrow.devplatform.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FileUpload {
    private String id ;
    private String userId ;
    private String filename ;
    private String password ;
    private String fileurl ;
}
