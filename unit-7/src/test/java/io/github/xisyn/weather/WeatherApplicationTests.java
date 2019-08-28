package io.github.xisyn.weather;

import io.github.xisyn.weather.weather.WeatherRetriever;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = WeatherApplication.class,
        properties = {
                InteractiveShellApplicationRunner
                        .SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner
                        .SPRING_SHELL_SCRIPT_ENABLED + "=false"
        }
)
public class WeatherApplicationTests {
    @Autowired
    WeatherRetriever weatherRetriever;

    @Test
    public void weather() {
        String weather = weatherRetriever.getWeather("Красноярск").main.getTemp();
        System.out.println(weather);
    }

}
