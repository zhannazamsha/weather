package weather.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import weather.client.LocationByIpClient;
import weather.client.WeatherByLocationClient;
import weather.domain.Location;
import weather.domain.Weather;
import weather.domain.WeatherResponse;
import weather.domain.repositories.WeatherResponseRepository;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CollectWeatherDataServiceTest {

    @Autowired
    CollectWeatherDataServiceImpl collectWeatherDataService;
    @MockBean
    private LocationByIpClient locationByIpClient;
    @MockBean
    private WeatherByLocationClient weatherByLocationClient;
    @MockBean
    private WeatherResponseRepository weatherResponseRepository;

    @Before
    public void setUp() {
        Location location = new Location().builder().lat(1f).lon(1f).city("City").build();
        Weather weather = new Weather().builder().main("sunny").build();
        WeatherResponse weatherResponse = new WeatherResponse().builder().ip("333.21.34.6").build();

        Mockito.when(locationByIpClient
                .getLocation(weatherResponse.getIp()))
                .thenReturn(location);
        weatherResponse.setLocation(location);
        Mockito.when(weatherByLocationClient
                .getWeatherByLocation(weatherResponse.getLocation()))
                .thenReturn(weather);

    }

    @Test
    public void collect_givenIp_returnWeather() {
        WeatherResponse weatherResponse = collectWeatherDataService.collect("333.21.34.6");
        assertThat(weatherResponse)
                .isNotNull();
        assertThat(weatherResponse.getLocation())
                .isNotNull();
        assertThat(weatherResponse.getWeather())
                .isNotNull();
        assertThat(weatherResponse.getLocation().getCity())
                .isEqualTo("City");
        assertThat(weatherResponse.getWeather().getMain())
                .isEqualTo("sunny");
    }

    @TestConfiguration
    static class CollectWeatherDataServiceTestContextConfiguration {

        @Bean
        public CollectWeatherDataServiceImpl collectWeatherDataService() {
            return new CollectWeatherDataServiceImpl();
        }
    }

}
