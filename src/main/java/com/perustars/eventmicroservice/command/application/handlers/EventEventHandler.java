package com.perustars.eventmicroservice.command.application.handlers;

import com.perustars.eventmicroservice.command.infrastructure.EventRegistry;
import com.perustars.eventmicroservice.command.infrastructure.EventRegistryRepository;
import com.perustars.event.contracts.events.EventRegistered;
import com.perustars.event.contracts.events.EventDeleted;
import com.perustars.event.contracts.events.EventEdited;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("eventRegistry")
public class EventEventHandler {
    private final EventRegistryRepository eventRegistryRepository;
    public EventEventHandler(EventRegistryRepository eventRegistryRepository){
        this.eventRegistryRepository = eventRegistryRepository;
    }

    @EventHandler
    public void on(EventRegistered event){
        eventRegistryRepository.save(new EventRegistry(
                event.getEventId(),
                event.getArtistId(),
                event.getType(),
                event.getDescription(),
                event.getDateTime(),
                event.getCost(),
                event.getImage(),
                event.getLink(),
                event.getCapacity()
        ));
    }

    @EventHandler
    public void on(EventEdited event){
        Optional<EventRegistry> EventRegistryOptional = eventRegistryRepository.getByEventId(event.getEventId());
        EventRegistryOptional.ifPresent(eventRegistryRepository::delete);
        eventRegistryRepository.save(new EventRegistry(
                event.getEventId(),
                event.getArtistId(),
                event.getType(),
                event.getDescription(),
                event.getDateTime(),
                event.getCost(),
                event.getImage(),
                event.getLink(),
                event.getCapacity()
        ));
    }

    @EventHandler
    public void on(EventDeleted event){
        Optional<EventRegistry> EventRegistryOptional = eventRegistryRepository.getByEventId(event.getEventId());
        EventRegistryOptional.ifPresent(eventRegistryRepository::delete);
    }

}
