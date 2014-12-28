package uk.co.resilientdatasystems.faststatelesapiauth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(basePackages = { "uk.co.resilientdatasystems.faststatelesapiauth"}, 
    excludeFilters = {
        @Filter(value=Configuration.class, type = FilterType.ANNOTATION)
    })
public class MainConfig {

}
