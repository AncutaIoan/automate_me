package project.automate_me.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Window{

    private @Id @GeneratedValue Long id;
    private String name;
    private String position;
    private String state;
    private int angle;
    private String image;

    public Window() {}


    public Window(Long id, String name, String position, String state, int angle, String image) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.state = state;
        this.angle = angle;
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Window))
            return false;
        Window window = (Window) o;
        return Objects.equals(this.id, window.id) && Objects.equals(this.name, window.name)
                && Objects.equals(this.state, window.state)&& Objects.equals(this.position, window.position)
                && Objects.equals(this.angle, window.angle)&& Objects.equals(this.image, window.image);
    }


    @Override
    public String toString() {
        return "Window{" + "id=" + this.id + ", name='" + this.name + '\'' + ", state='" + this.state + '\''
                + ", position='" + this.position + '\''+ ", angle='" + this.angle + '\''+ ", image='" + this.image + '\''+ '}';
    }
}