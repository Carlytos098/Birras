package ar.com.birra.service;

import ar.com.birra.client.WeatherClient;
import ar.com.birra.client.model.SomeDaysWeather;
import ar.com.birra.exceptions.BadRequestException;
import ar.com.birra.model.Birras;
import ar.com.birra.model.Meetup;
import ar.com.birra.model.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static ar.com.birra.utils.Date.*;

/**
 * @author Carlos Moreno
 */
@Service
public class BirrasServiceImpl implements BirrasService {
    final double litrosPorCajas = 6.0;
    final int maxDifDays = 17;

    final WeatherClient weatherClient;
    final MeetupService meetService;

    @Autowired
    public BirrasServiceImpl(final WeatherClient weatherClient, MeetupService meetService) {
        this.weatherClient = weatherClient;
        this.meetService = meetService;
    }

    @Override
    public Birras getQuantityBoxesToBuy(String meetupID) {
        Meetup meetup = meetService.getMeetupById(meetupID);
        SomeDaysWeather someDaysWeather = weatherClient.getSomeDaysWeather();
        Temperature temperature = getTemperatureForDate(meetup.getDateOfMeet(), someDaysWeather.getList());
        double literPerPerson = getLiterPerPerson(temperature);
        Birras birra = new Birras();
        birra.setNumberOfBoxes((int) Math.ceil((literPerPerson * meetup.getNumberOfGuests()) / litrosPorCajas));
        birra.setTemperature(temperature);
        birra.setDateOfMeet(meetup.getDateOfMeet().toString());
        birra.setNumberOfGuests(meetup.getNumberOfGuests());
        return birra;
    }

    @Override
    public Temperature getTemperaturePerMeetup(String meetupID) {
        Meetup meetup = meetService.getMeetupById(meetupID);
        SomeDaysWeather someDaysWeather = weatherClient.getSomeDaysWeather();
        return getTemperatureForDate(meetup.getDateOfMeet(), someDaysWeather.getList());
    }

    private double getLiterPerPerson(Temperature temperature) {
        double litros = 2;
        try {
            int sensacionTermica = (int) Math.ceil(temperature.getFeels_like());
            if (sensacionTermica < 20) {
                litros = 0.75;
            } else if (sensacionTermica <= 24) {
                litros = 1;
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            litros = -1;
        }
        return litros;
    }

    private Temperature getTemperatureForDate(Date meetupDate, List<SomeDaysWeather.ListWeather> listWeather) {
        Temperature.Builder temperature = new Temperature.Builder();
        listWeather.stream()
                .filter(dayWeather ->
                        isValidMaxDifDays(meetupDate, new Date(dayWeather.getDt() * 1000L)))
                .forEach(dayWeather ->
                        temperature.setDate(meetupDate.toString()).setTemperature(dayWeather.getTemp().getDay())
                                .setFeels_like(dayWeather.getFeels_like().getDay()));
        return temperature.build();
    }

    private boolean isValidMaxDifDays(Date meetupDate, Date dateWeather) {
        if (GetDaysBetween(new Date(), meetupDate) < 0 || GetDaysBetween(new Date(), meetupDate) > maxDifDays) {
            throw new BadRequestException("La meet debe estar máximo de los 17 próximos días.");
        }
        return GetDateWithoutTime(meetupDate).compareTo(GetDateWithoutTime(dateWeather)) == 0;
    }
}