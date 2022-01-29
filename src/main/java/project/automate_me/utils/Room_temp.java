package main.java.project;

public class Room_temp {
    private final String id;
    private final float average_temp;

    public Room_temp(String id, float average_temp) {
        this.id = id;
        this.average_temp = average_temp;
    }

    public String getId() {
        return id;
    }

    public float getAverage_temp() {
        return average_temp;
    }

}
