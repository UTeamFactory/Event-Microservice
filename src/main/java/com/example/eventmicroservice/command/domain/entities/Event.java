package com.example.eventmicroservice.command.domain.entities;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Calendar;

@Aggregate
public class Event {

    @AggregateIdentifier
    private String eventId;
    private String artistId;
    private String type;
    private String description;
    private Calendar dateTime;
    private String cost;
    private String image;
    private String link;
    private String capacity;

    public Event(){}

}
