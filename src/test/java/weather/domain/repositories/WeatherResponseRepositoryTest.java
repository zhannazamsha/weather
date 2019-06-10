package weather.domain.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import weather.domain.Location;
import weather.domain.Weather;
import weather.domain.WeatherResponse;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WeatherResponseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WeatherResponseRepository weatherResponseRepository;

    @Test
    public void saveWeatherResponce_saveWeatherResponce_shouldBeSavedAndFoundById() {
        WeatherResponse weatherResponse = buildWeatherResponseObject();
        entityManager.persist(weatherResponse);
        entityManager.flush();
        Optional<WeatherResponse> weatherResponseFound = weatherResponseRepository.findById(weatherResponse.getId());
        assertThat(weatherResponseFound.isPresent());
    }

    @Test
    public void findByIp_oneEntityAdded_shouldBeFound() {
        WeatherResponse weatherResponse = buildWeatherResponseObject();
        entityManager.persist(weatherResponse.getLocation());
        entityManager.persist(weatherResponse.getWeather());
        entityManager.persist(weatherResponse);
        entityManager.flush();
        List<WeatherResponse> loanApplications = weatherResponseRepository.
                findByIp("333.21.34.6");
        assertThat(loanApplications).isNotEmpty();
        assertThat(loanApplications.size()).isEqualTo(1);
    }

    @Test
    public void findByLocation_oneEntityAdded_shouldBeFound() {
        WeatherResponse weatherResponse = buildWeatherResponseObject();
        entityManager.persist(weatherResponse.getLocation());
        entityManager.persist(weatherResponse.getWeather());
        entityManager.persist(weatherResponse);
        entityManager.flush();
        List<WeatherResponse> loanApplications = weatherResponseRepository.
                findByLocation(1f, 1f);
        assertThat(loanApplications).isNotEmpty();
        assertThat(loanApplications.size()).isEqualTo(1);
    }

    @Test
    public void findByLocation_oneEntityAdded_shouldNotBeFound() {
        WeatherResponse weatherResponse = buildWeatherResponseObject();
        entityManager.persist(weatherResponse.getLocation());
        entityManager.persist(weatherResponse.getWeather());
        entityManager.persist(weatherResponse);
        entityManager.flush();
        List<WeatherResponse> loanApplications = weatherResponseRepository.
                findByLocation(2f, 2f);
        assertThat(loanApplications).isEmpty();
    }


    private WeatherResponse buildWeatherResponseObject() {
        Location location = new Location().builder().lat(1f).lon(1f).city("City").build();
        Weather weather = new Weather().builder().main("sunny").build();
        WeatherResponse weatherResponse = new WeatherResponse().builder().ip("333.21.34.6").build();
        weatherResponse.setLocation(location);
        weatherResponse.setWeather(weather);
        return weatherResponse;
    }
}
