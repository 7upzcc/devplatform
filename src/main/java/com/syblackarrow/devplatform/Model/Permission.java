package com.syblackarrow.devplatform.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class Permission {
    private String id ;
    private String permission ;
    private String roleId ;
}
