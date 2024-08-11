package com.marketlow.products.domain.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.application")
public class GlobalProperties {
    private String name;
    private String version;
    private int port;
    private String root;
}
