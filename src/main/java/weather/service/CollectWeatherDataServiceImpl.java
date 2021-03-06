package weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import weather.client.LocationByIpClient;
import weather.client.WeatherByLocationClient;
import weather.domain.WeatherResponse;
import weather.domain.repositories.WeatherResponseRepository;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class CollectWeatherDataServiceImpl implements CollectWeatherDataService {

    @Autowired
    private LocationByIpClient locationByIpClient;

    @Autowired
    private WeatherByLocationClient weatherByLocationClient;

    @Autowired
    private WeatherResponseRepository weatherResponseRepository;


    @Override
    @Cacheable(value = "responses", key = "#ip")
    public WeatherResponse collect(String ip) throws IOException {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setIp(ip);
        weatherResponse.setResponseDate(LocalDate.now());
        //3rd part api return status 200 in any case - success or error
        weatherResponse.setLocation(locationByIpClient
                .getLocation(weatherResponse.getIp()));
        weatherResponse.setWeather(weatherByLocationClient
                .getWeatherByLocation(weatherResponse.getLocation()));
        weatherResponseRepository.save(weatherResponse);
        return weatherResponse;
    }

    @Scheduled(fixedRate = 3600000)
    @CacheEvict(value = {"responses"})
    public void clearCache() {
        //usually minimal step in weather prediction is 1h, so the weather information could be changed
    }
}
