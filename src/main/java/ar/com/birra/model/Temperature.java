package ar.com.birra.model;

public class Temperature {
    private String date;
    private double temperature;
    private double feels_like;

    private Temperature(String date, double temperature, double feels_like) {
        this.date = date;
        this.temperature = temperature;
        this.feels_like = feels_like;
    }

    public static class Builder {
        private String date;
        private double temperature;
        private double feels_like;

        public Builder() {
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setTemperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder setFeels_like(double feels_like) {
            this.feels_like = feels_like;
            return this;
        }

        public Temperature build() {
            return new Temperature(date, temperature, feels_like);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }
}
