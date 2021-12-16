package com.ojdgaf.cloud.user.mongodb;

import com.ojdgaf.cloud.user.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

@Profile("mongodb")
public interface UserMongodbRepository extends MongoRepository<User, String> {

}
