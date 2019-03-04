package com.syblackarrow.devplatform.Model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
@Setter
public class User implements Serializable {
    private String id ;
    private String username ;
    private String password ;
    private String content ;
}
