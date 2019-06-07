package weather.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import weather.domain.WeatherResponse;


public interface WeatherResponseRepository extends CrudRepository<WeatherResponse, Long> {
}
