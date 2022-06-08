package com.example.eventmicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Calendar;

@Entity
public class EventView {

    @Id
    @Getter @Setter
    private String eventId;
    @Column(length = 36) @Getter @Setter
    private String artistId;
    @Column(length = 75) @Getter @Setter
    private String type;
    @Column() @Getter @Setter
    private Calendar dateTime;
    @Column() @Getter @Setter
    private String cost;
    @Column(length = 200) @Getter @Setter
    private String description;

    @Column(length = 200) @Getter @Setter
    private String link;

    @Column() @Getter @Setter
    private String image;

    @Column() @Getter @Setter
    private String capacity;

    private Instant createdAt;

    @Column(nullable = true) @Getter
    @Setter
    private Instant updatedAt;

    public EventView(){}

    public EventView(String eventId, String artistId, String type, Calendar dateTime, String cost, String description, String link, String image, String capacity, Instant createdAt) {
        this.eventId = eventId;
        this.artistId = artistId;
        this.type = type;
        this.dateTime = dateTime;
        this.cost = cost;
        this.description = description;
        this.link = link;
        this.image = image;
        this.capacity = capacity;
        this.createdAt = createdAt;
    }
}
