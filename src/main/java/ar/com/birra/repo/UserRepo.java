package ar.com.birra.repo;

import ar.com.birra.repo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author Carlos
 */
public interface UserRepo extends MongoRepository<User, Long> {
    @Query("{ 'username' : ?0 }")
    User findUsersByUsername(String username);

    @Query("{ '_ID' : ?0 }")
    User findUserByID(String ID);

    @Query("{ 'username' : ?0 , 'token' : ?1 }")
    List<User> findUsersByUsernameAndToken(String username, String token);
}