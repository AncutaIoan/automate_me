package project.automate_me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.automate_me.model.*;

public interface TemperatureRepository extends JpaRepository<Temperature, Long>{
    Temperature getByRoom(String room);

}
