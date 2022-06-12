package com.example.eventmicroservice.command.application.validators;

import com.example.eventmicroservice.command.application.dtos.request.CreateEventRequest;
import com.example.eventmicroservice.command.infrastructure.EventRegistryRepository;
import com.example.eventmicroservice.common.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class CreateEventValidator {
    private final EventRegistryRepository eventRegistryRepository;
    public CreateEventValidator(EventRegistryRepository eventRegistryRepository) {
        this.eventRegistryRepository = eventRegistryRepository;
    }

    public Notification validate(CreateEventRequest createEventRequest) {
        Notification notification = new Notification();

        String description = createEventRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("Event description is required");
        }
        String type = createEventRequest.getType().trim();
        if (type.isEmpty()) {
            notification.addError("Event type is required");
        }


        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
