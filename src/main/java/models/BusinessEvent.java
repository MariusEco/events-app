package models;

public class BusinessEvent extends Event {
    private String industry;

    public BusinessEvent(String title, String date, String description, String location, String industry) {
        super(title, date, description, location);
        this.industry = industry;
    }

    @Override
    public String toString() {
        return super.toString() + " | Industry: " + industry;
    }

}
