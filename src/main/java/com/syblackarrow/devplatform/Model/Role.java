package com.syblackarrow.devplatform.Model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Role {
    private String userId ;
    private String role ;
}
