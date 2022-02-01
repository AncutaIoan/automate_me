package project.automate_me.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import project.automate_me.model.SmartWindow;
import project.automate_me.model.SmokeGasLevels;
import project.automate_me.model.User;
import project.automate_me.model.Weather;

public interface SmokeGasLevelsRepository extends JpaRepository<SmokeGasLevels, Long>{

}