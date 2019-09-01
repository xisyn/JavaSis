package io.github.xisyn.weather.weather;

public class WeatherValue {

    private String temp;

    public String getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return "WeatherValue{" +
                "temp='" + temp + '\'' +
                '}';
    }

}
