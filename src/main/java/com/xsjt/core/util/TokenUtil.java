package com.xsjt.core.util;

import cn.hutool.json.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xsjt.order.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TokenUtil {

    public static String getToken(User user) {
        long currentTime = System.currentTimeMillis();
        log.info("=====getToken===now    is " + new Date(currentTime));
        log.info("=====getToken===expire is " + new Date(currentTime + 30 * 1000 * 60));
        String token="";
        JSONObject object = new JSONObject(user.getJson());
        String username = object.getStr("username");
        String password = object.getStr("password");
        token= JWT.create()
                .withAudience(username)
                .withIssuedAt(new Date(currentTime))// 签发时间
                .withExpiresAt(new Date(currentTime + 30 * 1000 * 60))// 过期时间戳
                .sign(Algorithm.HMAC256(password));
        return token;
    }

}
