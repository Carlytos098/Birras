package ar.com.birra.service;

import ar.com.birra.controller.MeetApiController;
import ar.com.birra.model.Meetup;

import java.util.List;

public interface MeetupService {
    List<Meetup> getAllMeetups();

    List<Meetup> getAllMeetups(String username, String token);

    Meetup getMeetupById(String id);

    Meetup createMeetup(String username, String token, MeetApiController.CreateMeetupRequest cantPersonas);

    Meetup registerMeetup(String username, String token, String id);

    Meetup checkInMeetup(String username, String token, String id);
}
