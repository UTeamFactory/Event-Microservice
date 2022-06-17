package com.perustars.eventmicroservice.command.application.validators;

import com.perustars.eventmicroservice.command.application.dtos.request.DeleteEventRequest;
import com.perustars.eventmicroservice.command.infrastructure.EventRegistryRepository;
import com.perustars.common.application.Notification;
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
