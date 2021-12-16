package com.ojdgaf.cloud.user.fs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ojdgaf.cloud.user.User;
import com.ojdgaf.cloud.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("fs")
public class UserFileSystemService implements UserService {

    @Value("${app.fs.upload-directory}")
    private String uploadDirectory;

    private final ObjectWriter userWriter;
    private final ObjectReader userReader;

    public UserFileSystemService(final ObjectWriter userWriter, final ObjectReader userReader) {
        this.userWriter = userWriter;
        this.userReader = userReader;
    }

    @Override
    public User find(final String id) {
        try {
            return userReader.readValue(getFile(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User create(final User user) {
        try {
            String id = UUID.randomUUID().toString();
            userWriter.writeValue(getFile(id), user.setId(id));
            return user;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(final String id) {
        getFile(id).delete();
    }

    private File getFile(final String id) {
        return Paths.get(uploadDirectory).resolve(id + ".json").toFile();
    }
}
