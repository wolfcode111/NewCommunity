package com.nowcoder.community.community.controller.advice;

import com.nowcoder.community.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//这个注解的意思就是只去扫描那些带有Controller注解的bean
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

     //用于修饰方法，该方法会在Controller出现异常后被调用，用于处理捕获到的异常
    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("服务器发生异常: " + e.getMessage());
        for(StackTraceElement element : e.getStackTrace()){
            logger.error(element.toString());
        }

        //查看是不是异步请求，也就是我到底返回的是一个html页面还是一个json字符串
        String xRequestedWith = request.getHeader("x-requested-with");
        if("XMLHttpRequest".equals(xRequestedWith)){
            //设置为一个普通的字符串
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJSONString(1,"服务器异常!"));
        }else{
            response.sendRedirect(request.getContextPath()+"/error");
        }
    }

}
