package io.erocurement.b2b.auth;



import io.erocurement.b2b.models.entity.User;
import io.erocurement.b2b.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.HashMap;

import java.util.Map.Entry;
import java.util.Set;
import java.util.*;


@Component
@Slf4j
public class InfoAdicionalToken implements TokenEnhancer {




    @Autowired
    private UserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {


        long i = 123L;
        //track a custom dependency



        User user = userService.findByUsername(authentication.getName());
        HashMap<String, Object> info = new HashMap<String, Object>();
        info.put("info_adicional", "ExtraInfo: ".concat(authentication.getName()));

        info.put("nombre", user.getFirtsName());
        info.put("apellido", user.getLastName());
        info.put("email", user.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}
