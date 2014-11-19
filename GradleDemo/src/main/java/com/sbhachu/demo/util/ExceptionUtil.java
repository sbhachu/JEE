package com.sbhachu.demo.util;

import com.sbhachu.demo.exception.ServerException;
import org.apache.log4j.Logger;

public class ExceptionUtil {
    private static Logger log = Logger.getLogger(ExceptionUtil.class);

    public static String getExceptionMessage(String message) {
        String username = "NONE";

        try {
            if (UserUtils.isAuthenticated())
                username = UserUtils.getAuthenticatedUser().getUsername();
        } catch (ServerException e) {
            log.error("Error while retrieving User Details", e);
        }

        return String.format("%s [User: %s] ", message, username);
    }
}

