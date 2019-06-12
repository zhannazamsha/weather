package weather.service;

import weather.domain.WeatherResponse;

import java.io.IOException;


public interface CollectWeatherDataService {
    WeatherResponse collect(String ip) throws IOException;
}
