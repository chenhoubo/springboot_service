package com.xsjt.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
//@RefreshScope
@Component
@ConfigurationProperties("detail.info")
public class UserInfoProperties {

    private String name;

    private String age;

    private String gender;

    private String address;

    private String email;

}
