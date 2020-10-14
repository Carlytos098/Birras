package ar.com.birra.controller;

import ar.com.birra.model.Birras;
import ar.com.birra.model.Temperature;
import ar.com.birra.service.BirrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author Carlos Moreno
 */
@RestController
@RequestMapping("/api/birra")
@Validated
public class BirrasApiController {

    private final BirrasService birrasService;

    @Autowired
    public BirrasApiController(final BirrasService birrasService) {
        this.birrasService = birrasService;
    }

    //Como admin quiero saber cuántas cajas de birras tengo que comprar para poder aprovisionar la meetup.
    @RequestMapping(value = "/number-of-boxes-to-buy", method = RequestMethod.GET, produces = "application/json")
    public Birras numberOfBoxesToBuy(@RequestHeader(name = "username") @NotEmpty String username,
                                     @RequestHeader(name = "token") @NotEmpty String token,
                                     @RequestParam("meetupID") @NotEmpty String meetupID) {
        return birrasService.getQuantityBoxesToBuy(meetupID);
    }

    //Como admin y usuario quiero conocer la temperatura del día de la meetup para saber si va a hacer calor o no.
    @RequestMapping(value = "/temperature-per-meetup", method = RequestMethod.GET, produces = "application/json")
    public Temperature temperaturePerDay(@RequestHeader(name = "username") @NotEmpty String username,
                                         @RequestHeader(name = "token") @NotEmpty String token,
                                         @RequestParam("meetupID") @NotEmpty String meetupID) {
        return birrasService.getTemperaturePerMeetup(meetupID);
    }
}