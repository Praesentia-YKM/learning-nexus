package com.tdd.learn.externalApi.user;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {
    void save(User user);

    User findById(String id);
}
