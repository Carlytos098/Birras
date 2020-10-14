package ar.com.birra.controller;

import ar.com.birra.model.Meetup;
import ar.com.birra.service.MeetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Carlos Moreno
 */
@RestController
@RequestMapping("/api/meet")
@Validated
public class MeetApiController {

    private final MeetupService meetupService;
    private Object validateUserAndPassword;

    @Autowired
    public MeetApiController(final MeetupService meetupService) {
        this.meetupService = meetupService;
    }

    //Como usuario y como admin quiero poder recibir notificaciones para estar al tanto de las meetups.
    @RequestMapping(value = "/get-all-meetups", method = RequestMethod.GET, produces = "application/json")
    public List<Meetup> getAllMeetupsRegistered(@RequestHeader(name = "username") @NotEmpty String username,
                                                @RequestHeader(name = "token") @NotEmpty String token) {
        return meetupService.getAllMeetups(username, token);
    }

    //Como admin quiero armar una meetup para poder invitar otras personas.
    @RequestMapping(value = "/create-meetup", method = RequestMethod.POST, produces = "application/json")
    public Meetup createMeetup(@RequestHeader(name = "username") @NotEmpty String username,
                               @RequestHeader(name = "token") @NotEmpty String token,
                               @RequestBody @Valid CreateMeetupRequest createMeetupRequest) {
        return meetupService.createMeetup(username, token, createMeetupRequest);
    }

    //Como usuario quiero inscribirme en una meetup para poder asistir.
    @RequestMapping(value = "/register-meetup", method = RequestMethod.POST, produces = "application/json")
    public Meetup registerMeetup(@RequestHeader(name = "username") @NotEmpty String username,
                                 @RequestHeader(name = "token") @NotEmpty String token,
                                 @RequestBody @Valid MeetupRequest registerMeetupRequest) {
        return meetupService.registerMeetup(username, token, registerMeetupRequest.getId());
    }

    //Como usuario quiero hacer check-in en una meetup para poder avisar que estuve ahí.
    @RequestMapping(value = "/check-in-meetup", method = RequestMethod.POST, produces = "application/json")
    public Meetup checkInMeetup(@RequestHeader(name = "username") @NotEmpty String username,
                                @RequestHeader(name = "token") @NotEmpty String token,
                                @RequestBody @Valid MeetupRequest registerMeetupRequest) {
        return meetupService.checkInMeetup(username, token, registerMeetupRequest.getId());
    }

    public static class CreateMeetupRequest {
        private String id;
        @NotBlank
        private String nameOfMeet;
        @NotBlank
        @Pattern(regexp = "([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}", message = "date debe tener el siguiente formato dd/MM/yyyy")
        private String dateOfMeet;
        @NotEmpty
        @Pattern(regexp = "([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]", message = "hour debe tener el siguiente formato HH:mm")
        private String hourOfMeet;
        private List<String> guests;
        @NotBlank
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNameOfMeet() {
            return nameOfMeet;
        }

        public void setNameOfMeet(String nameOfMeet) {
            this.nameOfMeet = nameOfMeet;
        }

        public String getDateOfMeet() {
            return dateOfMeet;
        }

        public void setDateOfMeet(String dateOfMeet) {
            this.dateOfMeet = dateOfMeet;
        }

        public String getHourOfMeet() {
            return hourOfMeet;
        }

        public void setHourOfMeet(String hourOfMeet) {
            this.hourOfMeet = hourOfMeet;
        }

        public List<String> getGuests() {
            return guests;
        }

        public void setGuests(List<String> guests) {
            this.guests = guests;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class MeetupRequest {
        @NotBlank
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}