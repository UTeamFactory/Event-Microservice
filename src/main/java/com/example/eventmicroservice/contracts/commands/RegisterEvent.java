package com.example.eventmicroservice.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Calendar;

@Value
public class RegisterEvent {
    @TargetAggregateIdentifier
    private String eventId;
    private String artistId;
    private String type;
    private String description;
    private Calendar dateTime;
    private Double cost;
    private String image;
    private String link;
    private Double capacity;

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

    public Double getCost() {
        return cost;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public Double getCapacity() {
        return capacity;
    }

    public RegisterEvent(String eventId, String artistId, String type, String description, Calendar dateTime, Double cost, String image, String link, Double capacity) {
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
