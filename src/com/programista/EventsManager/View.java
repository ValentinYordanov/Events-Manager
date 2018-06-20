package com.programista.EventsManager;

import java.nio.file.Paths;
import java.util.Scanner;

public class View {

    public static final String DATETIMEFORMAT = "dd-MM-yyyy HH:mm";

    private EventManager manager;

    public View() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the directory you want to store the events in");
        EventManager manager = new EventManager(Paths.get(sc.nextLine()));
        this.manager = manager;
    }

    public void showCommandsList() {
        System.out.println(1 + " Read all events");
        System.out.println(2 + " Create an event");
        System.out.println(3 + " Update an event");
        System.out.println(4 + " Delete an event");
        System.out.println(5 + " Exit");
    }

    public Event enterEvent() {
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Please enter event name, location, start and end in format: " + View.DATETIMEFORMAT);
        String name = sc2.nextLine();
        String location = sc2.nextLine();
        String startTime = sc2.nextLine();
        String endTime = sc2.nextLine();

        return new Event(name, location, DateTimeParser.parseToLocalDateTime(startTime, DATETIMEFORMAT),
                DateTimeParser.parseToLocalDateTime(endTime, DATETIMEFORMAT), this.manager.getDirectory());
    }


    public void start() {

        int option = 0;

        Scanner sc = new Scanner(System.in);

        while(option != 5) {
            System.out.println("Please select an option:");
            showCommandsList();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    manager.readAllEvents(); break;
                case 2:
                    manager.createAnEvent(enterEvent()); break;
                case 3:
                    System.out.println("Please, first enter the information of the event you want to update, and then input the updated info");
                    manager.updateEvent(enterEvent(), enterEvent()); break;
                case 4:
                    manager.deleteEvent(enterEvent()); break;
                case 5:
                    break;
                default:
                    System.out.println("Unknown command! Please try again!");
            }
        }
    sc.close();
    }

    public static void main(String[] args) {

        View view = new View();

        view.start();

    }

}
