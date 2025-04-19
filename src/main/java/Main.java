import models.*;
import services.EventService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        EventService service = new EventService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEvent Management System");
            System.out.println("1. Add new event");
            System.out.println("2. List all available events");
            System.out.println("3. Remove event");
            System.out.println("4. Update event details");
            System.out.println("5. Add ticket for an event");
            System.out.println("6. List all available tickets for an event");
            System.out.println("7. Search events");
            System.out.println("8. Add sponsors");
            System.out.println("9. List all-time sponsors and their contributions");
            System.out.println("10. Add participants/volunteers for an event");
            System.out.println("11. Count participants/volunteers for an event");
            System.out.println("0. Exit");
            System.out.print("Choose an action: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    String eventType = "";
                    boolean validType = false;
                    while (!validType) {
                        System.out.print("Enter event type (sport/cultural/business): ");
                        eventType = scanner.nextLine();
                        try {
                            if (!eventType.equalsIgnoreCase("sport") && !eventType.equalsIgnoreCase("cultural") && !eventType.equalsIgnoreCase("business")) {
                                throw new IllegalArgumentException("Invalid event type. Please enter a valid type: sport, cultural or business.");
                            }
                            validType = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    String extraInfo;
                    if (eventType.equalsIgnoreCase("sport")) {
                        System.out.print("Enter sport type: ");
                        extraInfo = scanner.nextLine();
                    } else if (eventType.equalsIgnoreCase("cultural")) {
                        System.out.print("Enter cultural theme: ");
                        extraInfo = scanner.nextLine();
                    } else if (eventType.equalsIgnoreCase("business")) {
                        System.out.print("Enter industry: ");
                        extraInfo = scanner.nextLine();
                    } else {
                        System.out.println("Invalid event type");
                        return;
                    }

                    System.out.print("Enter event title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter date: ");
                    String date = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    service.addEvent(title, date, desc, location, eventType, extraInfo);
                }
                case 2 -> service.listAllEvents();
                case 3 -> {
                    System.out.print("Enter event title to remove: ");
                    String title = scanner.nextLine();
                    service.removeEvent(title);
                }
                case 4 -> {
                    System.out.print("Enter event title to update: ");
                    String title = scanner.nextLine();
                    boolean exists = service.getEvents().stream()
                            .anyMatch(e -> e.getTitle().equalsIgnoreCase(title));
                    if (!exists) {
                        System.out.println("No event found with that title.");
                    } else {
                        System.out.print("New date: ");
                        String date = scanner.nextLine();
                        System.out.print("New description: ");
                        String desc = scanner.nextLine();
                        System.out.print("New location: ");
                        String location = scanner.nextLine();
                        service.updateEvent(title, date, desc, location);
                    }
                }
                case 5 -> {
                    System.out.print("Enter event title: ");
                    String eventTitle = scanner.nextLine();
                    Event foundEvent = service.findEventByTitle(eventTitle);
                    if (foundEvent == null) {
                        System.out.println("No event found with that title.");
                    } else {
                        System.out.print("Enter ticket type: ");
                        String type = scanner.nextLine();
                        double price = 0;
                        boolean validPrice = false;
                        while (!validPrice) {
                            System.out.print("Enter ticket price: ");
                            try {
                                price = scanner.nextDouble();
                                scanner.nextLine();
                                validPrice = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid price. Please enter a valid number.");
                                scanner.nextLine();
                            }
                        }
                        service.addTicketForEvent(foundEvent, type, price);
                    }
                }
                case 6 -> {
                    System.out.print("Enter event title: ");
                    String eventTitle = scanner.nextLine();
                    Event foundEvent = service.findEventByTitle(eventTitle);
                    if (foundEvent == null) {
                        System.out.println("No event found with that title.");
                    } else {
                        service.showTicketsForEvent(eventTitle);
                    }
                }
                case 7 -> {
                    System.out.print("Search here (you can search by name/location/exact date): ");
                    String keyword = scanner.nextLine();
                    service.searchEvents(keyword);
                }
                case 8 -> {
                    System.out.print("Enter sponsor name: ");
                    String name = scanner.nextLine();
                    double sponsoredAmount = 0;
                    boolean validAmount = false;
                    while (!validAmount) {
                        System.out.print("Enter sponsored amount: ");
                        try {
                            sponsoredAmount = scanner.nextDouble();
                            scanner.nextLine();
                            validAmount = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid amount. Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                    Sponsor newSponsor = new Sponsor(name, sponsoredAmount);
                    service.addSponsor(newSponsor);
                }
                case 9 -> service.listSponsorsSorted();
                case 10 -> {
                    System.out.print("Enter event title: ");
                    String eventTitle = scanner.nextLine();
                    Event foundEvent = service.findEventByTitle(eventTitle);
                    if (foundEvent == null) {
                        System.out.println("No event found with that title.");
                    }
                    else {
                        System.out.print("Add (1) participant or (2) volunteer? ");
                        int type = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        if (type == 2) {
                            System.out.print("Enter volunteer role: ");
                            String role = scanner.nextLine();
                            service.addVolunteerToEvent(foundEvent, name, email, role);
                        } else {
                            service.addParticipantToEvent(foundEvent, name, email);
                        }
                    }
                }
                case 11 -> {
                    System.out.print("Enter event title: ");
                    String eventTitle = scanner.nextLine();
                    Event foundEvent = service.findEventByTitle(eventTitle);
                    if (foundEvent == null) {
                        System.out.println("No event found with that title.");
                    }
                    else {
                        service.countParticipantsAndVolunteersForEvent(eventTitle);
                    }
                }
                case 0 -> {
                    return;
                }
            }
        }
    }
}