package weather.domain.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import weather.domain.WeatherResponse;

import java.util.List;


public interface WeatherResponseRepository extends CrudRepository<WeatherResponse, Long> {

    List<WeatherResponse> findByIp(@Param("ip") String ip);

    @Query("select a from WeatherResponse a where a.location.lat = :lat and a.location.lon >= :lon")
    List<WeatherResponse> findByLocation(@Param("lat") Float lat,
                                         @Param("lon") Float lon);

}
