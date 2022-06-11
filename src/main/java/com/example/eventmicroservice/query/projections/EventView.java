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
    @Column(length = 75) @Getter @Setter
    private String type;
    @Column(length = 200) @Getter @Setter
    private String description;
    @Column() @Getter @Setter
    private Calendar dateTime;
    @Column() @Getter @Setter
    private String cost;
    @Column() @Getter @Setter
    private String image;

    @Column(length = 200) @Getter @Setter
    private String link;

    @Column() @Getter @Setter
    private String capacity;

    private Instant createdAt;

    @Column(nullable = true) @Getter
    @Setter
    private Instant updatedAt;

    public EventView(){}

    public EventView(String eventId, String type, String description, Calendar dateTime, String cost, String image, String link, String capacity, Instant createdAt) {
        this.eventId = eventId;
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
        this.cost = cost;
        this.image = image;
        this.link = link;
        this.capacity = capacity;
        this.createdAt = createdAt;
    }
}
