package com.ojdgaf.cloud.user.s3;

import java.util.UUID;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.util.IOUtils;
import com.ojdgaf.cloud.user.User;
import com.ojdgaf.cloud.user.UserService;
import com.ojdgaf.cloud.user.exception.NotDeletedException;
import com.ojdgaf.cloud.user.exception.NotFoundException;
import com.ojdgaf.cloud.user.exception.NotSavedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("s3")
public class UserS3Service implements UserService {

    @Value("${app.s3.bucket-name}")
    private String bucketName;

    private final AmazonS3 client;

    public UserS3Service(final AmazonS3 client) {
        this.client = client;
    }

    @Override
    public User find(final String id) {
        try {
            String content = IOUtils.toString(client.getObject(bucketName, id).getObjectContent());
            return User.READER.readValue(content);
        } catch (final Exception e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    public User create(final User user) {
        return update(UUID.randomUUID().toString(), user);
    }

    @Override
    public User update(final String id, final User user) {
        try {
            user.setId(id);
            client.putObject(bucketName, id, User.WRITER.writeValueAsString(user));
            return user;
        } catch (final Exception e) {
            throw new NotSavedException(e);
        }
    }

    @Override
    public void delete(final String id) {
        try {
            client.deleteObject(bucketName, id);
        } catch (final SdkClientException e) {
            throw new NotDeletedException(e);
        }
    }
}
