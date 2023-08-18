package com.senai.segundoprojetoweb.authetication;
import com.senai.segundoprojetoweb.service.CookieService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if(CookieService.getCookie(request,"usuarioId") != null){
            return true;
        }else{
        response.sendRedirect("/login");
        return false;
        }
    }
}
