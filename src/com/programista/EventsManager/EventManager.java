package com.programista.EventsManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<Event> events = new ArrayList<>();

    private Path directory;

    public Path getDirectory() {
        return directory;
    }

    public EventManager(Path directory) {

        this.directory = directory;

        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(directory)) {
            for (Path fileOrSubDir : stream) {

                try (BufferedReader br = new BufferedReader(new FileReader(fileOrSubDir.toFile()))) {
                    String name = br.readLine();
                    String location = br.readLine();
                    String startTime = br.readLine();
                    String endTime = br.readLine();

                    events.add(new Event(name, location,
                            DateTimeParser.parseToLocalDateTime(startTime, View.DATETIMEFORMAT),
                            DateTimeParser.parseToLocalDateTime(endTime, View.DATETIMEFORMAT),
                            this.directory));
                }

            }
        } catch (DirectoryIteratorException x) {
            System.out.println("Problem with iteration files from directory");
        } catch (IOException e) {
            System.out.println("IO exeption");
        }

    }

    public void readAllEvents() {

        for (Event event : events) {
            event.print();
        }

    }

    public void createAnEvent(Event event) {

        events.add(event);

    }

    public void updateEvent(Event event, Event updatedEvent) {

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).equals(event)) {
                events.get(i).update(updatedEvent);
            }
        }

    }

    public void deleteEvent(Event event) {

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).equals(event)) {
                events.get(i).delete();
                events.remove(i);
                return;
            }
        }

    }
}
