package models;

public class SportEvent extends Event {
    private String sportType;

    public SportEvent(String title, String date, String description, String location, String sportType) {
        super(title, date, description, location);
        this.sportType = sportType;
    }

    @Override
    public String toString() {
        return super.toString() + " | Sport Type: " + sportType;
    }
}