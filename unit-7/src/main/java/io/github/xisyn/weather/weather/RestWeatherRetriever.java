package io.github.xisyn.weather.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestWeatherRetriever implements WeatherRetriever {

    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(RestWeatherRetriever.class);

    public RestWeatherRetriever(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public WeatherImportDTO getWeather(String city) {
        logger.debug("Request for weather temperature: " + city);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
        headers.add("x-rapidapi-key", "b76c131aa4msh861b05768650d7ap163401jsn9ce105d42c36");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("units", "metric");
        params.add("mode", "json");
        params.add("q", city);

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("community-open-weather-map.p.rapidapi.com")
                .path("/weather")
                .queryParams(params)
                .build();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<WeatherImportDTO> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, entity, WeatherImportDTO.class);
        logger.debug("Request completed with result: {}", response.getBody());
        return response.getBody();
    }
}
