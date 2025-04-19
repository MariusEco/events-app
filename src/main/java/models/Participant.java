package models;

public class Participant {
    protected String id;
    protected String name;
    protected String email;
    protected Event event;

    public Participant(String id, String name, String email, Event event) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return name + " (" + email + ") - " + id;
    }
}
