package com.sbhachu.demo.util;

import com.sbhachu.demo.exception.ServerException;
import com.sbhachu.demo.models.UserModel;
import com.sbhachu.demo.security.impl.ServerAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static UserModel getAuthenticatedUser() throws ServerException {
        Authentication authentication = getAuthentication();

        if (authentication instanceof ServerAuthenticationToken) {
            UserModel user = (UserModel) ((ServerAuthenticationToken) authentication).getPrincipal();
            return user;
        } else {
            throw new ServerException("Not Authenticated! Authentication object: " + authentication);
        }
    }

    public static boolean isAuthenticated() {
        Authentication authentication = getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
