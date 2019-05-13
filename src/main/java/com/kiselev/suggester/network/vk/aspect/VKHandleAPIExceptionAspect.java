package com.kiselev.suggester.network.vk.aspect;

import com.kiselev.suggester.network.vk.exception.ExceptionHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class VKHandleAPIExceptionAspect {

    @Pointcut("execution(* com.kiselev.suggester.view.rest.endpoint.SuggestionEndpoint.* (..))" +
            "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void endpoint() {
    }

    @Around("endpoint()")
    public Object handleAPIException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            ExceptionHandler.handleException(throwable);
            return null;
        }
    }
}
