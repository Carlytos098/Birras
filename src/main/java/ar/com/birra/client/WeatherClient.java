package ar.com.birra.client;

import ar.com.birra.client.model.OneDayWeather;
import ar.com.birra.client.model.SomeDaysWeather;

import java.util.Date;

public interface WeatherClient {
    OneDayWeather getWeather();

    SomeDaysWeather getSomeDaysWeather();
}
