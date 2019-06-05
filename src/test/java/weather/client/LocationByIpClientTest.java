package weather.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import weather.domain.Location;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application.properties")
public class LocationByIpClientTest {

    @Autowired
    private LocationByIpClient locationByIpClient;



    @TestConfiguration
    static class LocationByIpClientTestContextConfiguration {
        @Autowired
        private RestTemplateBuilder builder;

        @Bean
        public LocationByIpClient blacklistService() {
            return new LocationByIpClient(this.builder
                    .errorHandler(new RestTemplateResponseErrorHandler()));
        }
    }

    @Test
    public void getLocation_ipPassed_shouldReturnLocation() throws IOException {
        Location location = locationByIpClient.getLocation("24.48.0.1");
        assertThat(location.getCity())
                .isEqualTo("Saint-Leonard");
    }


}
