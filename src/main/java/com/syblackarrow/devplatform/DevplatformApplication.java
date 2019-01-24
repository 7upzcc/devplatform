package com.syblackarrow.devplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.syblackarrow.devplatform")
@SpringBootApplication(scanBasePackages={"com.syblackarrow.devplatform"})
public class DevplatformApplication {
	public static void main(String[] args) {
		SpringApplication.run(DevplatformApplication.class, args);
	}

}

