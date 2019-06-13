package weather.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weather.domain.WeatherResponse;
import weather.service.CollectWeatherDataService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
public class WeatherByIpController {

    @Autowired
    private CollectWeatherDataService collectWeatherDataService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeatherByIp(HttpServletRequest request) throws IOException {
        String ip = request.getRemoteAddr();
        ip = "77.38.182.232"; // for testing
        WeatherResponse response = collectWeatherDataService.collect(ip);
        return ResponseEntity.ok(response);
    }


}
