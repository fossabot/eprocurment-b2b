package io.erocurement.b2b.services;


import io.erocurement.b2b.models.entity.User;

public interface UserService {

    public User findByUsername(String username);

    public User save(User user);

    public Boolean existsByUsername(String username);

    boolean existsByEmail(String username);
}
