package ar.com.birra.service;

import ar.com.birra.model.Birras;
import ar.com.birra.model.Temperature;

public interface BirrasService {
    Birras getQuantityBoxesToBuy(String meetupID);

    Temperature getTemperaturePerMeetup(String meetupID);
}
