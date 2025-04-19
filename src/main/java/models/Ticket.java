package models;

public class Ticket {
    private String ticketId;
    private Event event;
    private double price;
    private String type;

    public Ticket(String ticketId, Event event, double price, String type) {
        this.ticketId = ticketId;
        this.event = event;
        this.price = price;
        this.type = type;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId +
                " | Event: " + event  +
                " | Type: " + type +
                " | Price: " + price + " RON";
    }
}
