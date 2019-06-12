package weather.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import weather.domain.Location;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application.properties")
public class LocationByIpClientTest {

    @Autowired
    private LocationByIpClient locationByIpClient;

    @TestConfiguration
    static class LocationByIpClientTestContextConfiguration {

        @Bean
        public LocationByIpClient locationByIpClientService() {
            return new LocationByIpClient();
        }
    }

    @Test
    public void getLocation_ipPassed_shouldReturnLocation(){
        Location location = locationByIpClient.getLocation("24.48.0.1");
        assertThat(location.getCity())
                .isEqualTo("Saint-Leonard");
    }


}
