package com.syblackarrow.devplatform.Model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class User {
    private String id ;
    private String username ;
    private String password ;
    private String content ;
}
