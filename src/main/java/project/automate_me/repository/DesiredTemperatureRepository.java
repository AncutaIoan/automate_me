package project.automate_me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.automate_me.model.*;

public interface DesiredTemperatureRepository extends JpaRepository<DesiredTemperature, Long> {
    DesiredTemperature getByRoom(String room);

}