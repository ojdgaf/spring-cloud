package com.ojdgaf.cloud.greeting;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {

    @Bean
    public ServiceInstanceListSupplier sameInstanceListSupplier(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier
            .builder()
            .withBlockingDiscoveryClient()
            .withSameInstancePreference()
            .build(context);
    }
}