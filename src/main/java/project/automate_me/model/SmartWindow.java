package project.automate_me.model;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class SmartWindow {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String position;
    private String state;
    private int angle;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;


    public SmartWindow() {
    }


    public SmartWindow(String name, String position, String state, int angle, String image, User user) {

        this.name = name;
        this.position = position;
        this.state = state;
        this.angle = angle;
        this.image = image;
        this.user = user;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof SmartWindow))
            return false;
        SmartWindow smartWindow = (SmartWindow) o;
        return Objects.equals(this.id, smartWindow.id) && Objects.equals(this.name, smartWindow.name)
                && Objects.equals(this.state, smartWindow.state) && Objects.equals(this.position, smartWindow.position)
                && Objects.equals(this.angle, smartWindow.angle) && Objects.equals(this.image, smartWindow.image)
                && Objects.equals(this.user, smartWindow.user);
    }

    @Override
    public String toString() {
        return "Window{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", state='" + state + '\'' +
                ", angle=" + angle +
                ", image='" + image + '\'' +
                ", user=" + user +
                '}';
    }
}