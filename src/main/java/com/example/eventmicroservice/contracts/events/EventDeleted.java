package com.example.eventmicroservice.contracts.events;

import lombok.Value;

import java.time.Instant;

@Value
public class EventDeleted {
    private String eventId;
    private Instant occurredOn;
}
