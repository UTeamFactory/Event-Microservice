package com.perustars.eventmicroservice.command.application.dtos.response;

import lombok.Value;

@Value
public class DeleteEventResponse {
    private String eventId;
}
