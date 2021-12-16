package com.ojdgaf.cloud.user;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {

    private String id;

    @NotBlank(message = "'firstname' is required")
    private String firstname;

    @NotBlank(message = "'lastname' is required")
    private String lastname;

    @NotBlank(message = "'email' is required")
    private String email;
}
