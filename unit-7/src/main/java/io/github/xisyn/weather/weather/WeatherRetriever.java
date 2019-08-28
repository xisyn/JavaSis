package io.github.xisyn.weather.weather;

public interface WeatherRetriever {
    WeatherImportDTO getWeather(String city);
}
