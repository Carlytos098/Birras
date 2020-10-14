package ar.com.birra.client;

import ar.com.birra.client.model.OneDayWeather;
import ar.com.birra.client.model.SomeDaysWeather;
import ar.com.birra.config.RestTemplateConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static java.lang.String.format;

@Service
public class WeatherClientImpl implements WeatherClient {

    private final RestTemplateConfiguration restTemplate;

    @Autowired
    public WeatherClientImpl(final RestTemplateConfiguration restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OneDayWeather getWeather() {
        final String uri = "https://community-open-weather-map.p.rapidapi.com/weather?lang=%s&units=%s&q=%s,%s";
        ResponseEntity<OneDayWeather> response = restTemplate.exchange(format(uri, "es", "metric", "buenos aires", "ar"),
                HttpMethod.GET, getHeaders(), OneDayWeather.class);
        return response.getBody();
    }

    public SomeDaysWeather getSomeDaysWeather() {
        final String uri = "https://community-open-weather-map.p.rapidapi.com/forecast/daily?q=%s,%s&cnt=%s&units=%s";
        ResponseEntity<SomeDaysWeather> response = restTemplate.exchange(format(uri, "buenos aires", "ar", "17", "metric"), HttpMethod.GET, getHeaders(), SomeDaysWeather.class);
        return response.getBody();
    }

    private static HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com");
        headers.set("X-RapidAPI-Key", "ef2b2fad4cmsh66b0bc5d7a605dep14f09djsn054e412da8b3");

        return new HttpEntity<>(headers);
    }
}