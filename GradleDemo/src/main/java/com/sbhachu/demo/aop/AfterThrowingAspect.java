package com.sbhachu.demo.aop;

import com.sbhachu.demo.exception.ServerException;
import com.sbhachu.demo.util.ExceptionUtil;
import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.security.authentication.BadCredentialsException;

public final class AfterThrowingAspect implements ThrowsAdvice {
    private static Logger log = Logger.getLogger(AfterThrowingAspect.class);

    public void afterThrowing(Exception ex) throws Throwable {
        if (!(ex instanceof BadCredentialsException) && (ex instanceof ServerException)) {
            ServerException exception = (ServerException) ex;

            if (exception.isLoggable())
                log.warn(ExceptionUtil.getExceptionMessage(ex.getMessage()), ex);
            else
                log.error(ExceptionUtil.getExceptionMessage(ex.getMessage()), ex);
        } else {
            log.error(ExceptionUtil.getExceptionMessage(ex.getMessage()), ex);
        }

        throw ex;
    }
}