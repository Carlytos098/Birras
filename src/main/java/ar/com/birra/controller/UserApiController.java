package ar.com.birra.controller;

import ar.com.birra.exceptions.HttpExceptionMapper;
import ar.com.birra.repo.model.User;
import ar.com.birra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Carlos Moreno
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/save/{userId}", method = RequestMethod.PUT, produces = "application/json")
    public User save(@PathVariable final String userId, @RequestBody User user) {
        return userService.save(userId, user);
    }

    @RequestMapping(value = "/list/all", method = RequestMethod.GET, produces = "application/json")
    public List<User> listAll() {
        return userService.listAll();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@RequestParam("username") String username) {
        userService.delete(username);
    }

    @RequestMapping(value = "/delete/all", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteAll() {
        userService.deleteAll();
    }
}