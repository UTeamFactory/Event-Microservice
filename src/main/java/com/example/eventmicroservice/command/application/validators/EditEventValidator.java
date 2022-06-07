package com.example.eventmicroservice.command.application.validators;

import com.example.eventmicroservice.command.application.dtos.request.EditEventRequest;
import com.example.eventmicroservice.command.domain.entities.Event;
import com.example.eventmicroservice.command.infrastructure.EventRegistryRepository;
import com.example.eventmicroservice.common.application.Notification;
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

    public Notification validate(EditEventRequest editArtworkRequest) {
        Notification notification = new Notification();
        String eventId = editArtworkRequest.getEventId().trim();
        if(eventId.isEmpty()) {
            notification.addError("Event id is required");
        }
        loadEventAggregate(eventId);

        String description = editArtworkRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("Event description is required");
        }
        String cost = editArtworkRequest.getCost().trim();
        if (cost.isEmpty()) {
            notification.addError("Event cost is required");
        }
        String type = editArtworkRequest.getType().trim();
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
