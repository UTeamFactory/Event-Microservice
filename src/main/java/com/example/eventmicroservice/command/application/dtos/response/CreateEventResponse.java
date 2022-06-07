package com.example.eventmicroservice.command.application.dtos.response;

import lombok.Value;

import java.util.Calendar;

@Value
public class CreateEventResponse {
    private String eventId;
    private String artistId;
    private String type;
    private String description;
    private Calendar dateTime;
    private String cost;
    private String image;
    private String link;
    private String capacity;
}
