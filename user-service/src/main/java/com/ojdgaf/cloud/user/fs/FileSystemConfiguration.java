package com.ojdgaf.cloud.user.fs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ojdgaf.cloud.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("fs")
public class FileSystemConfiguration {

    @Bean
    public ObjectWriter userWriter() {
        return new ObjectMapper().writerFor(User.class);
    }

    @Bean
    public ObjectReader userReader() {
        return new ObjectMapper().readerFor(User.class);
    }
}
