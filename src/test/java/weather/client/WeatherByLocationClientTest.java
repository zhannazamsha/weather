package weather.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import weather.domain.Location;
import weather.domain.Weather;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application.properties")
public class WeatherByLocationClientTest {
    @Autowired
    private WeatherByLocationClient weatherByLocationClient;



    @TestConfiguration
    static class WeatherByLocationClientTestContextConfiguration {

        @Bean
        public WeatherByLocationClient weatherByLocationClientService() {
            return new WeatherByLocationClient();
        }
    }

    @Test
    public void getLocation_locationPassed_shouldReturnLocation() throws IOException {
        Location location  = new Location();
        location.setLat(37.39f);
        location.setLon(-122.09f);
        Weather weather = weatherByLocationClient.getWeatherByLocation(location);
        assertThat(weather)
                .isNotNull();

    }


}
