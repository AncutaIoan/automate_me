package project.automate_me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.automate_me.model.User;
import project.automate_me.model.Weather;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findByCity(String city);
    Weather getByCity(String city);
    void deleteByCity(String city);
}
