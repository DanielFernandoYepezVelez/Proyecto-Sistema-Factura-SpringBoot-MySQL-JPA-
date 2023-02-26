package com.nextappoficial.springboot.app.invoice.sistem.Auth;

import com.nextappoficial.springboot.app.invoice.sistem.models.entity.UserAuth;
import com.nextappoficial.springboot.app.invoice.sistem.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdditionalTokenInformation implements TokenEnhancer {

    @Autowired
    private IUserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> information = new HashMap<>();
        UserAuth userAuth = userService.findByUsername(oAuth2Authentication.getName());

        information.put("Information_additional: ", "Hola Que Tal! " + userAuth.getUsername());
        information.put("Id: ", userAuth.getId());
        information.put("Name: ", userAuth.getName());
        information.put("LastName: ", userAuth.getLastName());
        information.put("Username: ", userAuth.getUsername());
        information.put("Password: ", userAuth.getPassword());
        information.put("Email: ", userAuth.getEmail());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(information);
        return oAuth2AccessToken;
    }
}
