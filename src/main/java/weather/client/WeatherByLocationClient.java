package weather.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import weather.domain.Location;
import weather.domain.Weather;

import java.io.IOException;

@Service
public class WeatherByLocationClient {

    @Value("${location.weather.url}")
    private String locationWeatherUrl;

    private RestTemplate restTemplate;

    @Autowired
    public WeatherByLocationClient() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

    }

    public Weather getWeatherByLocation(Location location) throws IOException {
        ResponseEntity<String> response
                = restTemplate.getForEntity(String.format(locationWeatherUrl,
                String.format("%.02f", location.getLat()), String.format("%.02f", location.getLon())),
                String.class);
        JsonNode productNode = new ObjectMapper().readTree(response.getBody());
        return weatherMapping(productNode);
    }

    private Weather weatherMapping(JsonNode productNode) {
        Weather weather = new Weather();
        weather.setHumidity(productNode.get("main").findValue("humidity").asText());
        weather.setMain(productNode.get("weather").findValue("description").asText());
        weather.setPressure(productNode.get("main").findValue("pressure").asText());
        weather.setTemp(productNode.get("main").findValue("temp").asText());
        weather.setWind(productNode.get("wind").findValue("speed").asText());
        return weather;
    }


}
