package com.ojdgaf.cloud.greeting;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "name-service", fallbackFactory = NameFeignFallbackFactory.class)
@LoadBalancerClient(name = "name-service", configuration = LoadBalancerConfiguration.class)
public interface NameFeignClient {

    @GetMapping
    String getDefaultName();
}