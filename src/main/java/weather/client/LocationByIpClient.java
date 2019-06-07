package weather.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import weather.domain.Location;


@Service
public class LocationByIpClient {

    @Value("${ip.location.url}")
    private String ipLocationUrl;
    private RestTemplate restTemplate;

    @Autowired
    public LocationByIpClient() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    }

    public Location getLocation(String ip) {
        ResponseEntity<Location> response
                = restTemplate.getForEntity(ipLocationUrl + ip, Location.class);
        return response.getBody();
    }
}
