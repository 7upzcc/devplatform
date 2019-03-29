package com.syblackarrow.devplatform.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
@Setter
public class UserSettings implements Serializable {
    private String id ;
    private String userId ;
    private String settingKey ;
    private String settingValue ;
}
