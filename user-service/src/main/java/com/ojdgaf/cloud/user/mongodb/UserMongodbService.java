package com.ojdgaf.cloud.user.mongodb;

import com.ojdgaf.cloud.user.User;
import com.ojdgaf.cloud.user.UserService;
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
        return repository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public User create(final User user) {
        return repository.save(user.setId(null));
    }

    @Override
    public User update(final String id, final User user) {
        return repository.save(user.setId(id));
    }

    @Override
    public void delete(final String id) {
        repository.deleteById(id);
    }
}
