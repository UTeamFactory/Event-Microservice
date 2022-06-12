package com.example.eventmicroservice.command.application.dtos.request;

import lombok.Value;

import java.util.Calendar;

@Value
public class CreateEventRequest {
    private String artistId;
    private String type;
    private String description;
    private Calendar dateTime;
    private Double cost;
    private String image;
    private String link;
    private Double capacity;
}
