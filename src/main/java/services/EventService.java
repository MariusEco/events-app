package services;

import models.*;

import java.util.*;

public class EventService {
    private List<Event> events = new ArrayList<>();
    private Map<String, Ticket> tickets = new HashMap<>();
    private Set<Sponsor> sponsors = new TreeSet<>(Comparator.comparing(Sponsor::getName));
    private List<Participant> participants = new ArrayList<>();
    private List<Volunteer> volunteers = new ArrayList<>();

    private final AuditService auditService = AuditService.getInstance();

    public void addEvent(String title, String date, String description, String location, String eventType, String extraInfo) {
        Event newEvent = null;
        if (eventType.equalsIgnoreCase("sport")) {
            newEvent = new SportEvent(title, date, description, location, extraInfo);
        } else if (eventType.equalsIgnoreCase("cultural")) {
            newEvent = new CulturalEvent(title, date, description, location, extraInfo);
        } else if (eventType.equalsIgnoreCase("business")) {
            newEvent = new BusinessEvent(title, date, description, location, extraInfo);
        }
        events.add(newEvent);
        System.out.println("Event added successfully!");
        auditService.logAction("Add event");
    }

    public void listAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
        auditService.logAction("List events");
    }

    public void removeEvent(String title) {
        boolean removed = events.removeIf(e -> e.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Event removed successfully!");
            auditService.logAction("Remove event");
        } else {
            System.out.println("No event found with that title.");
        }
    }

    public void updateEvent(String title, String newDate, String newDescription, String newLocation) {
        for (Event event : events) {
            if (event.getTitle().equalsIgnoreCase(title)) {
                event.setDate(newDate);
                event.setDescription(newDescription);
                event.setLocation(newLocation);
                break;
            }
        }
        auditService.logAction("Update event");
    }

    public void addTicketForEvent(Event event, String type, double price) {
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, event, price, type);
        tickets.put(ticketId, ticket);
        System.out.println("Ticket added successfully! Ticket ID: " + ticketId);
        auditService.logAction("Add ticket for event");
    }

    public void showTicketsForEvent(String title) {
        boolean found = false;
        for (Ticket ticket : tickets.values()) {
            if (ticket.getEvent().getTitle().equalsIgnoreCase(title)) {
                System.out.println(ticket);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tickets found for the event: " + title);
        }
        auditService.logAction("Show tickets for event");
    }

    public void searchEvents(String keyword) {
        boolean found = false;
        for (Event event : events) {
            if (event.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    event.getLocation().toLowerCase().contains(keyword.toLowerCase()) ||
                    event.getDate().equalsIgnoreCase(keyword)) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events found matching the keyword: " + keyword);
        }
        auditService.logAction("Search events");
    }

    public void addSponsor(Sponsor sponsor) {
        sponsors.add(sponsor);
        System.out.println("Sponsor added successfully!");
        auditService.logAction("Add sponsor");
    }

    public void listSponsorsSorted() {
        if (sponsors.isEmpty()) {
            System.out.println("No sponsors available.");
        } else {
            System.out.println("List of sponsors (sorted alphabetically):");
            sponsors.forEach(System.out::println);
        }
        auditService.logAction("List sponsors");
    }

    public void addParticipantToEvent(Event event, String name, String email) {
        String id = UUID.randomUUID().toString();
        participants.add(new Participant(id, name, email, event));
        System.out.println("Participant added successfully.");
        auditService.logAction("Add participant to event");
    }

    public void addVolunteerToEvent(Event event, String name, String email, String role) {
        String id = UUID.randomUUID().toString();
        volunteers.add(new Volunteer(id, name, email, event, role));
        System.out.println("Volunteer added successfully.");
        auditService.logAction("Add volunteer to event");
    }

    public void countParticipantsAndVolunteersForEvent(String eventTitle) {
        long participantCount = participants.stream()
                .filter(p -> p.getEvent().getTitle().equalsIgnoreCase(eventTitle))
                .count();

        long volunteerCount = volunteers.stream()
                .filter(v -> v.getEvent().getTitle().equalsIgnoreCase(eventTitle))
                .count();

        System.out.println("Participants for event \"" + eventTitle + "\": " + participantCount);
        System.out.println("Volunteers for event \"" + eventTitle + "\": " + volunteerCount);
        auditService.logAction("Count participants and volunteers for event");
    }

    public Event findEventByTitle(String title) {
        for (Event e : events) {
            if (e.getTitle().equalsIgnoreCase(title)) {
                return e;
            }
        }
        return null;
    }

    public List<Event> getEvents() {
        return events;
    }

}