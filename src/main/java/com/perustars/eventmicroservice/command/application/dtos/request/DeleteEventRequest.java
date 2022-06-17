package com.perustars.eventmicroservice.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class DeleteEventRequest {
    private @Getter
    @Setter
    String eventId;
}
