package models;

public class CulturalEvent extends Event {
    private String theme;

    public CulturalEvent(String title, String date, String description, String location, String theme) {
        super(title, date, description, location);
        this.theme = theme;
    }

    @Override
    public String toString() {
        return super.toString() + " | Cultural Theme: " + theme;
    }
}