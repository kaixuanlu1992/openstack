package com.wentt.openstack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.wentt.openstack.dao")
public class OpenstackApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenstackApplication.class, args);
    }

}

