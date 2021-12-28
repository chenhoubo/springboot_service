package com.xsjt.core.support.interceptor;

import com.auth0.jwt.JWT;
import com.xsjt.core.jackson.JsonUtil;
import com.xsjt.order.entity.User;
import com.xsjt.order.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@AllArgsConstructor
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    IUserService userServie;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz== User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String token = request.getHeader("auth");
        String username = JWT.decode(token).getAudience().get(0);
        User user = userServie.selectUserByUsername(username).getData();
        return user;
    }
}
