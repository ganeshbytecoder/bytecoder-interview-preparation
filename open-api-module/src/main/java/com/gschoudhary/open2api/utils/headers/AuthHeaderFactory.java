package com.gschoudhary.open2api.utils.headers;

import com.gschoudhary.open2api.domain.AuthConfigEntity;
import com.gschoudhary.open2api.enums.AuthType;
import com.gschoudhary.open2api.repository.AuthConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthHeaderFactory {


    public AuthHeaderService getAuthHeaderService(AuthType authType, Optional<AuthConfigEntity> authConfig) {
        if (authType.equals(AuthType.NO_AUTH)) {
            return new NoAuthHeaderServiceImpl();

        } else if (authType.equals(AuthType.OAUTH2)) {
            return new OAuth2HeaderServiceImpl(authConfig.get());
        } else if (authType.equals(AuthType.BASIC_AUTH)) {
            return new BasicAuthHeaderServiceImpl(authConfig.get());
        }
        return new NoAuthHeaderServiceImpl();

    }
}
