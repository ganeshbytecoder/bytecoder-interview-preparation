package com.gschoudhary.open2api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties
@Component
public class BasicConfiguration {
    private String DATABASE_URL;

    private String DATABASE_USERNAME;

    private String DATABASE_PASSWORD;

}
