package com.nowcoder.community.community.acpect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class ServiceLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);


    //不管返回值，所有的组件，所有的方法，所有的参数都使用下面
    @Pointcut("execution(* com.nowcoder.community.community.service.*.*(..))")
    public void pointcut(){
    }
    //在前置通知
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
       //用户[1.2.3.4]，在[xxx]，访问了[com.nowcoder.community.community.service.xxx]
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      if(attributes == null){
          return;
      }
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteHost();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String target = joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName();
        logger.info(String.format("用户[%s],在[%s],访问了[%s].",ip,now,target));
        System.out.println(String.format("用户[%s],在[%s],访问了[%s].",ip,now,target));
    }
}
