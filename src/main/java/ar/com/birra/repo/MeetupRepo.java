package ar.com.birra.repo;

import ar.com.birra.repo.model.Meetup;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author Carlos
 */
public interface MeetupRepo extends MongoRepository<Meetup, Long> {
    @Query("{ 'guestsUsers' : ?0 }")
    List<Meetup> findMeetupsByGuestUser(String owner);

    @Query("{ 'id' : ?0 }")
    Meetup findBySId(String id);
}