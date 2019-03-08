package com.syblackarrow.devplatform.Service;

import com.syblackarrow.devplatform.Model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Autowired
    UserService userService ;

    @Test
    public void getCurrentUser() {
        System.out.println(userService.getCurrentUser());
    }
}