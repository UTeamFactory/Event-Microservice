package com.example.eventmicroservice.query.projections;

import com.example.eventmicroservice.contracts.events.EventDeleted;
import com.example.eventmicroservice.contracts.events.EventEdited;
import com.example.eventmicroservice.contracts.events.EventRegistered;
import org.axonframework.eventhandling.EventHandler;

import java.util.Optional;

public class EventViewProjection {

    private final EventViewRepository eventViewRepository;

    public EventViewProjection(EventViewRepository eventViewRepository){
        this.eventViewRepository = eventViewRepository;
    }

    @EventHandler
    public void on(EventRegistered event){
        EventView eventView = new EventView(
                event.getEventId(),
                event.getArtistId(),
                event.getType(),
                event.getDateTime(),
                event.getCost(),
                event.getCapacity(),
                event.getLink(),
                event.getImage(),
                event.getDescription(),
                event.getOccurredOn()
        );
        eventViewRepository.save(eventView);
    }

    @EventHandler
    public void on(EventEdited event){
        Optional<EventView> eventViewOptional = eventViewRepository.findById(event.getEventId().toString());
        if (eventViewOptional.isPresent()){
            EventView eventView = eventViewOptional.get();
            eventView.setType(event.getType());
            eventView.setDescription(event.getDescription());
            eventView.setDateTime(event.getDateTime());
            eventView.setCost(event.getCost());
            eventView.setLink(event.getLink());
            eventView.setImage(event.getImage());
            eventView.setCapacity(event.getCapacity());
            eventView.setUpdatedAt(event.getOccurredOn());
            eventViewRepository.save(eventView);
        }
    }

    @EventHandler
    public void on(EventDeleted event){
        Optional<EventView> eventViewOptional = eventViewRepository.findById(event.getEventId().toString());
        if (eventViewOptional.isPresent()){
            EventView eventView = eventViewOptional.get();
            eventView.setEventId(event.getEventId());
            eventView.setUpdatedAt(event.getOccurredOn());
            eventViewRepository.save(eventView);
        }
    }

}
