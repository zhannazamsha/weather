package weather.service;

import weather.domain.WeatherResponse;


public interface CollectWeatherDataService {
    WeatherResponse collect(String ip);
}
