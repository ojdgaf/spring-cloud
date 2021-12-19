package com.ojdgaf.cloud.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Document("users")
public class User {

    public static final ObjectWriter WRITER = new ObjectMapper().writerFor(User.class);
    public static final ObjectReader READER = new ObjectMapper().readerFor(User.class);

    @Id
    private String id;

    @NotBlank(message = "'firstname' is required")
    private String firstname;

    @NotBlank(message = "'lastname' is required")
    private String lastname;

    @Email
    @NotBlank(message = "'email' is required")
    private String email;
}
