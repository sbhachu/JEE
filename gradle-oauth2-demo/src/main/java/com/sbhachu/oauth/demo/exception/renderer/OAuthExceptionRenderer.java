package com.sbhachu.oauth.demo.exception.renderer;

import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OAuthExceptionRenderer implements OAuth2ExceptionRenderer {
    private static final Logger LOGGER = Logger.getLogger(OAuthExceptionRenderer.class);

    public void handleHttpEntityResponse(HttpEntity<?> responseEntity, ServletWebRequest webRequest) throws Exception {

        if (responseEntity == null) {
            return;
        }

        HttpInputMessage inputMessage = createHttpInputMessage(webRequest);
        HttpOutputMessage outputMessage = createHttpOutputMessage(webRequest);
        LOGGER.info("filtering headers only...");
        if (responseEntity instanceof ResponseEntity && outputMessage instanceof ServerHttpResponse) {
            ((ServerHttpResponse) outputMessage).setStatusCode(((ResponseEntity<?>) responseEntity).getStatusCode());
        }
        HttpHeaders entityHeaders = responseEntity.getHeaders();
        if (!entityHeaders.isEmpty()) {
            outputMessage.getHeaders().putAll(entityHeaders);
        }
    }

    private HttpInputMessage createHttpInputMessage(NativeWebRequest webRequest) throws Exception {
        return new ServletServerHttpRequest(webRequest.getNativeRequest(HttpServletRequest.class));
    }

    private HttpOutputMessage createHttpOutputMessage(NativeWebRequest webRequest) throws Exception {
        HttpServletResponse servletResponse = (HttpServletResponse) webRequest.getNativeResponse();
        servletResponse.getWriter().write("blah");
        return new ServletServerHttpResponse(servletResponse);
    }
}
