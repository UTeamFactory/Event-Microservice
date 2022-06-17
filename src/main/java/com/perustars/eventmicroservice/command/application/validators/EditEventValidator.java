package com.perustars.eventmicroservice.command.application.validators;

import com.perustars.eventmicroservice.command.application.dtos.request.EditEventRequest;
import com.perustars.eventmicroservice.command.domain.entities.Event;
import com.perustars.eventmicroservice.command.infrastructure.EventRegistryRepository;
import com.perustars.common.application.Notification;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Component
public class EditEventValidator {
    private final EventRegistryRepository eventRegistryRepository;
    private final Repository<Event> eventRepository;

    public EditEventValidator(EventRegistryRepository eventRegistryRepository, Repository<Event> eventRepository) {
        this.eventRegistryRepository = eventRegistryRepository;
        this.eventRepository = eventRepository;
    }

    public Notification validate(EditEventRequest editEventRequest) {
        Notification notification = new Notification();
        String eventId = editEventRequest.getEventId().trim();
        if(eventId.isEmpty()) {
            notification.addError("Event id is required");
        }
        loadEventAggregate(eventId);

        String description = editEventRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("Event description is required");
        }

        String type = editEventRequest.getType().trim();
        if (type.isEmpty()) {
            notification.addError("Event type is required");
        }

        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    private void loadEventAggregate(String eventId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            eventRepository.load(eventId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }
}
