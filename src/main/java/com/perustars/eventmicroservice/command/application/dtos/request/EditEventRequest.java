package com.perustars.eventmicroservice.command.application.dtos.request;

import lombok.*;

import java.util.Calendar;

public class EditEventRequest {
    private @Getter @Setter String eventId;
    private @Getter String artistId;
    private @Getter String type;
    private @Getter String description;
    private @Getter Calendar dateTime;
    private @Getter Double cost;
    private @Getter String image;
    private @Getter String link;
    private @Getter Double capacity;
}
