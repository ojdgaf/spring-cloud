package com.ojdgaf.cloud.user;

public interface UserService {

    User find(String id);

    User create(User user);

    void delete(String id);
}
