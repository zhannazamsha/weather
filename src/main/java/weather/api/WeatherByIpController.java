package weather.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class WeatherByIpController {

    @GetMapping("/weather")
    public ResponseEntity getWeatherByIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        return ResponseEntity.ok("Sunny");
    }
}
