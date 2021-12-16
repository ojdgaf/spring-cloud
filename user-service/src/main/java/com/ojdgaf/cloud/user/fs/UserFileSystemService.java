package com.ojdgaf.cloud.user.fs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import com.ojdgaf.cloud.user.User;
import com.ojdgaf.cloud.user.UserService;
import com.ojdgaf.cloud.user.exception.NotDeletedException;
import com.ojdgaf.cloud.user.exception.NotFoundException;
import com.ojdgaf.cloud.user.exception.NotSavedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("fs")
public class UserFileSystemService implements UserService {

    @Value("${app.fs.upload-directory}")
    private String uploadDirectory;

    @Override
    public User find(final String id) {
        try {
            return User.READER.readValue(getFile(id));
        } catch (final IOException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public User create(final User user) {
        return update(UUID.randomUUID().toString(), user);
    }

    @Override
    public User update(final String id, final User user) {
        try {
            User.WRITER.writeValue(getFile(id), user.setId(id));
            return user;
        } catch (final IOException e) {
            throw new NotSavedException(e);
        }
    }

    @Override
    public void delete(final String id) {
        try {
            getFile(id).delete();
        } catch (final Exception e) {
            throw new NotDeletedException(e);
        }
    }

    private File getFile(final String id) {
        return Paths.get(uploadDirectory).resolve(id + ".json").toFile();
    }
}
