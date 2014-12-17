package com.sbhachu.oauth.demo.security;

import com.sbhachu.oauth.demo.model.User;
import com.sbhachu.oauth.demo.model.security.Role;
import com.sbhachu.oauth.demo.util.MD5EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhachu on 13/12/2014.
 */
public class TokenGenerator {

    private ClientDetailsService clientDetailsService;

    private DefaultTokenServices tokenServices;

    @Autowired
    public TokenGenerator(ClientDetailsService clientDetailsService, DefaultTokenServices tokenServices) {
        this.clientDetailsService = clientDetailsService;
        this.tokenServices = tokenServices;
    }

    public OAuth2AccessToken createToken(User user) {
        final String passwordHash = MD5EncoderUtil.encode(user.getPassword());
        final String clientId = SecurityContextHolder.getContext().getAuthentication().getName();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));

        if (user.getRole() == Role.ROLE_ADMIN) {
            authorities.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getName(), passwordHash, authorities);

        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);

        OAuth2Request oAuth2Request = new OAuth2Request(null, clientId, authorities, true,
                authenticatedClient.getScope(), null, null, null, null);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authenticationToken);

        return tokenServices.createAccessToken(oAuth2Authentication);
    }
}
