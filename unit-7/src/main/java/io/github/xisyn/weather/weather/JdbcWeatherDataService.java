package io.github.xisyn.weather.weather;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class JdbcWeatherDataService implements WeatherDataService {
    private final JdbcTemplate jdbcTemplate;

    public JdbcWeatherDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(String city, String temperature) {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        jdbcTemplate.update("insert into weathers (temperature) values (?)", String.join(" ", city, currentDate, temperature));
    }

    @Override
    public List<String> getAll() {
        return jdbcTemplate.query("select temperature from weathers",
                ((rs, rowNum) -> rs.getString("temperature")));
    }
}
