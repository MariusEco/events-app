package models;

public class Volunteer extends Participant{
    private String assignedRole;

    public Volunteer(String id, String name, String email, Event event, String assignedRole) {
        super(id, name, email, event);
        this.assignedRole = assignedRole;
    }

    @Override
    public String toString() {
        return super.toString() + " | Volunteer Role: " + assignedRole;
    }
}
