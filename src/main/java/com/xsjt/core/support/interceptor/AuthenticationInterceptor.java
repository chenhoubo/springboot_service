package com.xsjt.core.support.interceptor;

import cn.hutool.json.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.xsjt.core.exception.ServiceException;
import com.xsjt.core.ret.RetCode;
import com.xsjt.core.util.Func;
import com.xsjt.order.entity.User;
import com.xsjt.order.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    IUserService userServie;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws ServiceException {

        String token = httpServletRequest.getHeader("auth");// 从 http 请求头中取出 token

        // 执行认证
        if (token == null) {
            throw new ServiceException(RetCode.UNAUTHEN.getMsg());
        }
        // 获取 token 中的 unionId
        String username;
        try {
            username = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(RetCode.UNAUTHORIZED);
        }
        User user = userServie.selectUserByUsername(username).getData();
        JSONObject objects = new JSONObject(user.getJson());
        String password = objects.getStr("password");
        if (Func.isEmpty(user)) {
            throw new ServiceException(RetCode.UNAUTHZ.getMsg());
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(RetCode.UNAUTHORIZED);
        }
        return true;
    }
}