package com.example.eventmicroservice.contracts.events;

import lombok.Value;

import java.time.Instant;
import java.util.Calendar;

@Value
public class EventRegistered {
    private String eventId;
    private String artistId;
    private String type;
    private String description;
    private Calendar dateTime;
    private Double cost;
    private String image;
    private String link;
    private Double capacity;
    private Instant occurredOn;
}
