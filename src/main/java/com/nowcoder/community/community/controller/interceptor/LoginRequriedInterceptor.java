package com.nowcoder.community.community.controller.interceptor;

import com.nowcoder.community.community.annotation.LoginRequried;
import com.nowcoder.community.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

@Component
public class LoginRequriedInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    /*
    * 下面方法中Object是拦截的目标
    * 这是为了防止不安全的访问路径
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LoginRequried loginRequried = method.getAnnotation(LoginRequried.class);
            if(loginRequried != null && hostHolder.getUser() == null){
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }
        }
        return true;
    }
}
