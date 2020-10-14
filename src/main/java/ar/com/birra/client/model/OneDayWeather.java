package ar.com.birra.client.model;

import java.util.List;
import java.util.Map;

public class OneDayWeather {
    public Coord coord;
    public List<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Rain rain;
    public Clouds clouds;
    public int dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;

    public static class Main {
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;
    }

    public static class Wind {
        public double speed;
        public int deg;
    }

    public static class Rain {
        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Data [id=" + id + ", value=" + value + "]";
        }

        public static Rain fromMap(Map<String, String> properties) {
            Rain data = new Rain();
            data.setId(properties.get("id"));
            data.setValue(properties.get("value"));

            return data;
        }
    }

    public static class Clouds {
        public int all;
    }

    public static class Sys {
        public int type;
        public int id;
        public String country;
        public int sunrise;
        public int sunset;
    }
}
