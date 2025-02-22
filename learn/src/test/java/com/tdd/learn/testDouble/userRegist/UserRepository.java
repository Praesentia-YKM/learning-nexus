package com.tdd.learn.testDouble.userRegist;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
