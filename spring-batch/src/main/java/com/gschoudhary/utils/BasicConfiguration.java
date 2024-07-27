package com.gschoudhary.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties
public class BasicConfiguration {

    private String DATABASE_URL;
    private String DATABASE_USERNAME;
    private String DATABASE_PASSWORD;
    private List<String> ALLOWED_ORIGINS;
    private String JWT_SECRET_KEY;
}
