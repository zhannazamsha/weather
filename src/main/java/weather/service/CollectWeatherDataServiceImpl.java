package weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.client.LocationByIpClient;
import weather.client.WeatherByLocationClient;
import weather.domain.WeatherResponse;
import weather.domain.repositories.WeatherResponseRepository;

import java.io.IOException;

@Service
public class CollectWeatherDataServiceImpl implements CollectWeatherDataService {

    @Autowired
    private LocationByIpClient locationByIpClient;

    @Autowired
    private WeatherByLocationClient weatherByLocationClient;

    @Autowired
    private WeatherResponseRepository weatherResponseRepository;


    @Override
    public WeatherResponse collect(String ip) {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setIp(ip);
        weatherResponse.setLocation(locationByIpClient.getLocation(weatherResponse.getIp()));
        weatherResponse.setWeather(weatherByLocationClient
                .getWeatherByLocation(weatherResponse.getLocation()));
        weatherResponseRepository.save(weatherResponse);
        return weatherResponse;
    }
}