package ar.com.birra.service;

import ar.com.birra.repo.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User save(String userId, User user);

    List<User> listAll();

    void delete(String username);

    void deleteAll();

    User getUsersByUsername(String username);

    boolean isValid(String username, String token);
}
