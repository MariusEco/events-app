package models;

public class Event {
    protected String title;
    protected String date;
    protected String description;
    protected String location;

    public Event(String title, String date, String description, String location) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return title + " | Date: " + date + " | Description: " + description + " | Location: " + location;
    }

}
