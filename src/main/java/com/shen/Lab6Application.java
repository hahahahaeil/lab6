package com.shen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.shen.mapper")
@SpringBootApplication
public class Lab6Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab6Application.class, args);
    }

}
