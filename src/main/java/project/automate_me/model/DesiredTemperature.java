package project.automate_me.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DesiredTemperature {
    private @Id
    Long id;
    private String room;
    private int degrees;

    public DesiredTemperature() {
    }

    public DesiredTemperature(Long id, String room, int degrees) {
        this.id = id;
        this.room = room;
        this.degrees = degrees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }
}