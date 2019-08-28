package io.github.xisyn.weather.cli;

import io.github.xisyn.weather.weather.JdbcWeatherDataService;
import io.github.xisyn.weather.weather.WeatherRetriever;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@ShellComponent
public class ShellCommands {
    private final WeatherRetriever weatherService;
    private final JdbcWeatherDataService weatherDataService;

    private String currentTemperature;

    public ShellCommands(WeatherRetriever weatherRetriever, JdbcWeatherDataService weatherDataService) {
        this.weatherService = weatherRetriever;
        this.weatherDataService = weatherDataService;
    }

    @ShellMethod("Get city current weather temperature and save it")
    public String weatherTemp(String city) {
        currentTemperature = weatherService.getWeather(city).main.getTemp();
        weatherDataService.save(city, currentTemperature);
        return currentTemperature;
    }

    @ShellMethod("Save city current weather temperature")
    public String save(String city) {
        weatherDataService.save(city ,weatherService.getWeather(city).main.getTemp());
        return "saved";
    }

    @ShellMethod("Show all saved")
    public String show() {
        return weatherDataService.getAll().stream().collect(Collectors.joining(System.lineSeparator()));
    }
}
