package com.ojdgaf.cloud.name;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

    @Value("${app.default-name:Undefined}")
    private String defaultName;

    @GetMapping
    public String getDefaultName() {
        return defaultName;
    }
}
