package weather.domain.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import weather.domain.WeatherResponse;

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


    private WeatherResponse buildWeatherResponseObject() {
        return new WeatherResponse().builder().build();

    }
}
