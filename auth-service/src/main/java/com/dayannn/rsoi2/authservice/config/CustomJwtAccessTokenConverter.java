package com.dayannn.RSOI2.authservice.config;

import com.dayannn.RSOI2.authservice.model.UserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);
        Authentication userAuthentication = authentication.getUserAuthentication();

        if (userAuthentication != null) {
            LinkedHashMap userDetails = (LinkedHashMap) map.get("userDetails");
            if (userDetails != null) {

                // build your principal here
                String localUserTableField = (String) userDetails.get("localUserTableField");
                UserInfo extendedPrincipal = new UserInfo();

                Collection<? extends GrantedAuthority> authorities = userAuthentication.getAuthorities();

                userAuthentication = new UsernamePasswordAuthenticationToken(extendedPrincipal,
                        userAuthentication.getCredentials(), authorities);
            }
        }
        return new OAuth2Authentication(authentication.getOAuth2Request(), userAuthentication);
    }
}
