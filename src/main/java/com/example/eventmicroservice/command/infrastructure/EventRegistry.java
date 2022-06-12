package com.example.eventmicroservice.command.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class EventRegistry {
    @Id
    @Column
    private String eventId;
    private String artistId;
    private String type;
    private String description;
    private Calendar dateTime;
    private Double cost;
    private String image;
    private String link;
    private Double capacity;

    public EventRegistry(){}

    public EventRegistry(String eventId, String artistId, String type, String description, Calendar dateTime, Double cost, String image, String link, Double capacity) {
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

    public String getEventId() {
        return eventId;
    }

    public EventRegistry setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getArtistId() {
        return artistId;
    }

    public EventRegistry setArtistId(String artistId) {
        this.artistId = artistId;
        return this;
    }

    public String getType() {
        return type;
    }

    public EventRegistry setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventRegistry setDescription(String description) {
        this.description = description;
        return this;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public EventRegistry setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Double getCost() {
        return cost;
    }

    public EventRegistry setCost(Double cost) {
        this.cost = cost;
        return this;
    }

    public String getImage() {
        return image;
    }

    public EventRegistry setImage(String image) {
        this.image = image;
        return this;
    }

    public String getLink() {
        return link;
    }

    public EventRegistry setLink(String link) {
        this.link = link;
        return this;
    }

    public Double getCapacity() {
        return capacity;
    }

    public EventRegistry setCapacity(Double capacity) {
        this.capacity = capacity;
        return this;
    }
}
