package com.ojdgaf.cloud.greeting;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class GreetingController {

    @Autowired
    private NameFeignClient nameFeignClient;

    @GetMapping
    public String getGreeting() {
        log.info("getGreeting() endpoint called");
        return "Hello from " + nameFeignClient.getDefaultName();
    }
}
