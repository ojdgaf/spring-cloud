package com.ojdgaf.cloud.user.mongodb;

import com.ojdgaf.cloud.user.User;
import com.ojdgaf.cloud.user.UserService;
import com.ojdgaf.cloud.user.exception.NotDeletedException;
import com.ojdgaf.cloud.user.exception.NotFoundException;
import com.ojdgaf.cloud.user.exception.NotSavedException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mongodb")
public class UserMongodbService implements UserService {

    private final UserMongodbRepository repository;

    public UserMongodbService(final UserMongodbRepository repository) {
        this.repository = repository;
    }

    @Override
    public User find(final String id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User create(final User user) {
        return update(null, user);
    }

    @Override
    public User update(final String id, final User user) {
        try {
            return repository.save(user.setId(id));
        } catch (final Exception e) {
            throw new NotSavedException(e);
        }
    }

    @Override
    public void delete(final String id) {
        try {
            repository.deleteById(id);
        } catch (final Exception e) {
            throw new NotDeletedException(e);
        }
    }
}
