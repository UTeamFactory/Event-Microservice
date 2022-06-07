package com.example.eventmicroservice.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Calendar;

@Value
public class EditEvent {

    @TargetAggregateIdentifier
    private String eventId;
    private String artistId;
    private String type;
    private String description;
    private Calendar dateTime;
    private String cost;
    private String image;
    private String link;
    private String capacity;

    public String getEventId() {
        return eventId;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public String getCost() {
        return cost;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getCapacity() {
        return capacity;
    }

    public EditEvent(String eventId, String artistId, String type, String description, Calendar dateTime, String cost, String image, String link, String capacity) {
        this.eventId = eventId;
        this.artistId = artistId;
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
        this.cost = cost;
        this.image = image;
        this.link = link;
        this.capacity = capacity;
    }
}
