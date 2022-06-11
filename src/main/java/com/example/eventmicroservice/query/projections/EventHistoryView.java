package com.example.eventmicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Calendar;

@Entity
public class EventHistoryView {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long eventHistoryId;
    @Getter @Setter
    private String eventId;
    @Column(length = 36) @Getter @Setter
    private String artistId;
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

    @Getter @Setter
    private Instant createdAt;

    public EventHistoryView(){}

    public EventHistoryView(String eventId, String artistId, String type, String description, Calendar dateTime, String cost, String image, String link,  String capacity, Instant createdAt) {
        this.eventId = eventId;
        this.artistId = artistId;
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
        this.cost = cost;
        this.image = image;
        this.link = link;
        this.capacity = capacity;
        this.createdAt = createdAt;
    }

    public EventHistoryView(EventHistoryView eventHistoryView) {
        this.eventId = eventHistoryView.getEventId();
        this.artistId = eventHistoryView.getArtistId();
        this.type = eventHistoryView.getType();
        this.description = eventHistoryView.getDescription();
        this.dateTime = eventHistoryView.getDateTime();
        this.cost = eventHistoryView.getCost();
        this.image = eventHistoryView.getImage();
        this.link = eventHistoryView.getLink();
        this.capacity = eventHistoryView.getCapacity();
        this.createdAt = eventHistoryView.getCreatedAt();
    }
}
