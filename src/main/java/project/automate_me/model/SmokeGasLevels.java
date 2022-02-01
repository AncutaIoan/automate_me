package project.automate_me.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SmokeGasLevels {
    private @Id
    Long id;
    private int smoke;
    private int gas;
    public SmokeGasLevels(){

    }
    public SmokeGasLevels(Long id, int smoke, int gas) {
        this.id = id;
        this.smoke = smoke;
        this.gas = gas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSmoke() {
        return smoke;
    }

    public void setSmoke(int smoke) {
        this.smoke = smoke;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }
}
