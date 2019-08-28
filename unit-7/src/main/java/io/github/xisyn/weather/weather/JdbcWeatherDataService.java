package io.github.xisyn.weather.weather;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = dateFormat.format(date);

        jdbcTemplate.update("insert into weathers (temperature) values (?)", String.join(" ", city, formattedDate, temperature));
    }

    @Override
    public List<String> getAll() {
        return jdbcTemplate.query("select temperature from weathers",
                ((rs, rowNum) -> rs.getString("temperature")));
    }
}
