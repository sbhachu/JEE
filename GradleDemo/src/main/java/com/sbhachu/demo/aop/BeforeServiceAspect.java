package com.sbhachu.demo.aop;

import com.sbhachu.demo.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public final class BeforeServiceAspect implements MethodBeforeAdvice {
    private static Logger log = Logger.getLogger(BeforeServiceAspect.class);

    public void before(Method method, Object[] args, Object target) throws Throwable {

        String username = "NONE";

        if (UserUtils.isAuthenticated())
            username = UserUtils.getAuthenticatedUser().getUsername();

        log.info(String.format("%s:%s [User: %s]", target.getClass().getName(), method.getName(), username));
    }
}