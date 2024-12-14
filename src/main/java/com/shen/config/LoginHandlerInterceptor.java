package com.shen.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// 继承了拦截器接口，写了一个控制器之前被调用的拦截器
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        登录成功之后，有用户的session

         Object admin =  request.getSession().getAttribute("user");
         if(admin == null) {
             request.setAttribute("msg","没有权限，请先登录");
             request.getRequestDispatcher("/user/login").forward(request, response);
             return false;
         }else{
             return true;
         }

    }

}
