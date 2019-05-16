package com.kiselev.suggester.network.vk.aspect;

import com.kiselev.suggester.network.vk.utils.VKUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class VKCallTimeoutAspect {

    @Pointcut("execution(* com.kiselev.suggester.network.vk.implementation.VKAPI.* (..))")
    public void callApi() {
    }

    @Before("callApi()")
    public void sleepAfterCallVKAPI() {
        VKUtils.timeout();
    }
}
