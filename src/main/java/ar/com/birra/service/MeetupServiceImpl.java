package ar.com.birra.service;

import ar.com.birra.client.WeatherClient;
import ar.com.birra.controller.MeetApiController;
import ar.com.birra.exceptions.BadRequestException;
import ar.com.birra.exceptions.NotFoundException;
import ar.com.birra.exceptions.UnauthorizedException;
import ar.com.birra.exceptions.UnexpectedException;
import ar.com.birra.model.Meetup;
import ar.com.birra.repo.MeetupRepo;
import ar.com.birra.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ar.com.birra.utils.Date.ConvertStringToDate;
import static ar.com.birra.utils.Date.GetHoursBetween;

/**
 * @author Carlos Moreno
 */
@Service
public class MeetupServiceImpl implements MeetupService {
    final WeatherClient weatherClient;
    final MeetupRepo meetupRepo;
    final UserRepo userRepo;
    final UserService userService;

    @Autowired
    public MeetupServiceImpl(final WeatherClient weatherClient, final MeetupRepo meetupRepo, final UserRepo userRepo, final UserService userService) {
        this.weatherClient = weatherClient;
        this.meetupRepo = meetupRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @Override
    public List<Meetup> getAllMeetups() {
        List<ar.com.birra.repo.model.Meetup> meetups = meetupRepo.findAll();
        if (meetups.size() == 0) {
            throw new NotFoundException("No existen meetups registradas");
        }
        return meetups.stream().map(this::getMeetupToGenericMeetup).collect(Collectors.toList());
    }

    @Override
    public Meetup getMeetupById(String id) {
        ar.com.birra.repo.model.Meetup meetup = meetupRepo.findBySId(id);
        if (meetup == null) {
            throw new NotFoundException("No se encontró la Meet");
        }
        return getMeetupToGenericMeetup(meetup);
    }

    @Override
    public List<Meetup> getAllMeetups(String username, String token) {
        validateUser(username, token);
        return getAllMeetups();
    }

    @Override
    public Meetup createMeetup(String username, String token, MeetApiController.CreateMeetupRequest request) {
        validateUser(username, token);
        if (!userService.getUsersByUsername(username).getRole().equals("admin")) {
            throw new UnauthorizedException("Solo los administradores pueden crear una meet");
        }
        ar.com.birra.repo.model.Meetup m = new ar.com.birra.repo.model.Meetup();
        m.setName(request.getNameOfMeet());
        try {
            m.setDate(ConvertStringToDate(request.getDateOfMeet() + " " + request.getHourOfMeet()));
        } catch (ParseException e) {
            throw new UnexpectedException("Problema al manejar la fecha");
        }
        m.setGuestsUsers(request.getGuests());
        m.setOwner(username);
        m.setAddress(request.getAddress());
        return getMeetupToGenericMeetup(meetupRepo.save(m));
    }

    @Override
    public Meetup registerMeetup(String username, String token, String id) {
        validateUser(username, token);
        ar.com.birra.repo.model.Meetup m = meetupRepo.findBySId(id);
        Meetup meetup;
        if (m.getGuestsUsers() != null && m.getGuestsUsers().contains(username)) {
            meetup = getMeetupToGenericMeetup(m);
        } else {
            validateDate(m.getDate());
            if (m.getGuestsUsers() == null) {
                m.setGuestsUsers(Collections.singletonList(username));
            } else if (!m.getGuestsUsers().contains(username)) {
                m.getGuestsUsers().add(username);
            }
            meetup = getMeetupToGenericMeetup(meetupRepo.save(m));
        }
        return meetup;
    }

    @Override
    public Meetup checkInMeetup(String username, String token, String id) {
        validateUser(username, token);
        ar.com.birra.repo.model.Meetup m = meetupRepo.findBySId(id);
        Meetup meetup;
        if (m.getCheckinUsers() != null && m.getCheckinUsers().contains(username)) {
            meetup = getMeetupToGenericMeetup(m);
        } else {
            validateDate(m.getDate());
            if (m.getCheckinUsers() == null) {
                m.setCheckinUsers(Collections.singletonList(username));
            } else if (!m.getCheckinUsers().contains(username)) {
                m.getCheckinUsers().add(username);
            }
            meetup = getMeetupToGenericMeetup(meetupRepo.save(m));
        }
        return meetup;
    }

    private Meetup getMeetupToGenericMeetup(ar.com.birra.repo.model.Meetup m) {
        return new Meetup.Builder()
                .setId(m.getId())
                .setNameOfMeet(m.getName())
                .setDateOfMeet(m.getDate())
                .setNumberOfGuest(m.getGuestsUsers() == null ? 0 : m.getGuestsUsers().size())
                .setGuests(m.getGuestsUsers())
                .setCheckIn(m.getCheckinUsers())
                .setOwner(m.getOwner())
                .setAddress(m.getAddress())
                .build();
    }

    void validateUser(String username, String token) {
        if (!userService.isValid(username, token)) {
            throw new UnauthorizedException("Usuario no autorizado");
        }
    }

    void validateDate(Date dt) {
        if (GetHoursBetween(new Date(), dt) < 0) {
            throw new BadRequestException("No puede registrarse o hacer check in a una meetup que ya empezó o finalizó");
        }
    }
}