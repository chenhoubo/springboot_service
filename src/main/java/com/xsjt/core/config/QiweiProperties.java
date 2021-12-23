package com.xsjt.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
//@RefreshScope
@Component
@ConfigurationProperties("weixin.qy")
public class QiweiProperties {

    private String corpid;

    private String corpsecret;

    private String agentId;

    private String accessTokenUrl;

    private String sendMessageUrl;

}
