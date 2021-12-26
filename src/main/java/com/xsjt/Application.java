package com.xsjt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan({"com.xsjt.order.mapper.one","com.xsjt.order.mapper.two"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}