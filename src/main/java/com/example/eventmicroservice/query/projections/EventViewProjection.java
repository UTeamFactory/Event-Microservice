package com.example.eventmicroservice.query.projections;

import com.example.eventcontracts.contracts.events.EventDeleted;
import com.example.eventcontracts.contracts.events.EventEdited;
import com.example.eventcontracts.contracts.events.EventRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventViewProjection {

    private final EventViewRepository eventViewRepository;

    public EventViewProjection(EventViewRepository eventViewRepository){
        this.eventViewRepository = eventViewRepository;
    }

    @EventHandler
    public void on(EventRegistered event){
        EventView eventView = new EventView(
                event.getEventId(),
                event.getType(),
                event.getDescription(),
                event.getDateTime(),
                event.getCost(),
                event.getImage(),
                event.getLink(),
                event.getCapacity(),
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
            eventView.setImage(event.getImage());
            eventView.setLink(event.getLink());
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
