package com.example.eventmicroservice.command.application.validators;

import com.example.eventmicroservice.command.application.dtos.request.DeleteEventRequest;
import com.example.eventmicroservice.command.application.infrastructure.EventRegistryRepository;
import com.example.eventmicroservice.common.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class DeleteEventValidator {
    private final EventRegistryRepository eventRegistryRepository;
    public DeleteEventValidator(EventRegistryRepository eventRegistryRepository) {
        this.eventRegistryRepository = eventRegistryRepository;
    }

    public Notification validate(DeleteEventRequest deleteEventRequest) {
        Notification notification = new Notification();

        String id = deleteEventRequest.getEventId().trim();
        if (id.isEmpty()) {
            notification.addError("Event does not exist");
        }

        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
