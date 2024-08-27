package com.husony.identity_service.repository;

import com.husony.identity_service.pojo.User;

import java.util.List;

public interface UserRepository {
    User getUserByUsername(String username);

    boolean authUser(String username, String password);

    User addUser(User user);

    List<User> getUsers();
}
