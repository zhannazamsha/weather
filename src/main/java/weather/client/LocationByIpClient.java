package weather.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import weather.domain.Location;

import java.io.IOException;

@Service
public class LocationByIpClient {

    @Value("${ip.location.url}")
    private String ipLocationUrl;
    private RestTemplate restTemplate;

    @Autowired
    public LocationByIpClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }


    public Location getLocation(String ip) throws IOException {
        restTemplate.getErrorHandler().handleError(null);
        ResponseEntity<Location> response
                = restTemplate.getForEntity(ipLocationUrl + ip, Location.class);
        return response.getBody();
    }
}
