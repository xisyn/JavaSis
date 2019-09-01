package io.github.xisyn.weather.weather;

import java.util.List;

public interface WeatherDataService {
    void save(String city, String temperature);

    List<String> getAll();
}
