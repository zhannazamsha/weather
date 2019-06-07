package weather.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weather.domain.WeatherResponse;
import weather.service.CollectWeatherDataService;

import javax.servlet.http.HttpServletRequest;


@RestController
public class WeatherByIpController {

    @Autowired
    private CollectWeatherDataService collectWeatherDataService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeatherByIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        //  ip = "24.48.0.1";
        WeatherResponse response = collectWeatherDataService.collect(ip);
        return ResponseEntity.ok(response);
    }
}
