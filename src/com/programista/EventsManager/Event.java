package com.programista.EventsManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Event {

    private String name;
    private String location;
    private LocalDateTime startOfEvent;
    private LocalDateTime endOfEvent;
    private File file;

    public Event(String name, String location, LocalDateTime startOfEvent, LocalDateTime endOfEvent, Path directory) {
        this.name = name;
        this.location = location;
        this.startOfEvent = startOfEvent;
        this.endOfEvent = endOfEvent;
        this.file = new File(directory + "\\" + name + ".txt");

        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(name);
            pw.println(location);
            pw.println(DateTimeParser.parseToString(startOfEvent, View.DATETIMEFORMAT));
            pw.println(DateTimeParser.parseToString(endOfEvent, View.DATETIMEFORMAT));
        } catch (FileNotFoundException e) {
            System.out.println("File not found, trying to create an event");
        }
    }

    public void delete() {

        this.file.delete();

    }

    public void update(Event updatedEvent) {

        this.name = updatedEvent.name;
        this.location = updatedEvent.location;
        this.startOfEvent = updatedEvent.startOfEvent;
        this.endOfEvent = updatedEvent.endOfEvent;

        this.file.delete();
        this.file = updatedEvent.file;

    }

    void print() {

        System.out.println("name: " + this.name);
        System.out.println("location: " + this.location);
        System.out.println("start of event: " + DateTimeParser.parseToString(startOfEvent, View.DATETIMEFORMAT));
        System.out.println("end of event: " + DateTimeParser.parseToString(endOfEvent, View.DATETIMEFORMAT) + "\n");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (location != null ? !location.equals(event.location) : event.location != null) return false;
        if (startOfEvent != null ? !startOfEvent.equals(event.startOfEvent) : event.startOfEvent != null) return false;
        if (endOfEvent != null ? !endOfEvent.equals(event.endOfEvent) : event.endOfEvent != null) return false;
        return file != null ? file.equals(event.file) : event.file == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (startOfEvent != null ? startOfEvent.hashCode() : 0);
        result = 31 * result + (endOfEvent != null ? endOfEvent.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }
}
