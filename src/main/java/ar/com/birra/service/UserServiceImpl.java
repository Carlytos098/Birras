package ar.com.birra.service;

import ar.com.birra.repo.UserRepo;
import ar.com.birra.repo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Carlos Moreno
 */
@Service
public class UserServiceImpl implements UserService {
    final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User save(User user) {
        return userRepo.save(userRepo.findUsersByUsername(user.getUsername()));
    }

    public User save(String userId, User user) {
        user.setId(userId);
        User u = userRepo.findUserByID(userId);
        if (u != null) {
            user = userRepo.save(u);
        } else {
            user = userRepo.save(user);
        }
        return user;
    }

    public List<User> listAll() {
        return userRepo.findAll();
    }

    public void delete(String username) {
        //userRepo.findUsersByUsername(username).forEach(userRepo::delete);
        userRepo.delete(userRepo.findUsersByUsername(username));
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }

    public User getUsersByUsername(String username) {
        return userRepo.findUsersByUsername(username);
    }

    public boolean isValid(String username, String token) {
        boolean isValid = false;
        if (userRepo.findUsersByUsernameAndToken(username, token).size() > 0) {
            isValid = true;
        }
        return isValid;
    }
}