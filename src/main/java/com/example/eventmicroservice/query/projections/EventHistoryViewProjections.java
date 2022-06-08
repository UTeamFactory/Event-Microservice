package com.example.eventmicroservice.query.projections;

import com.example.eventmicroservice.contracts.events.EventDeleted;
import com.example.eventmicroservice.contracts.events.EventEdited;
import com.example.eventmicroservice.contracts.events.EventRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventHistoryViewProjections {
    private final EventHistoryViewRepository eventHistoryViewRepository;

    public EventHistoryViewProjections(EventHistoryViewRepository eventHistoryViewRepository) {
        this.eventHistoryViewRepository = eventHistoryViewRepository;
    }

    @EventHandler
    public void on(EventRegistered event){
        EventHistoryView eventHistoryView = new EventHistoryView(
                event.getEventId(),
                event.getArtistId(),
                event.getDescription(),
                event.getDateTime(),
                event.getImage(),
                event.getCapacity(),
                event.getCost(),
                event.getType(),
                event.getLink(),
                event.getOccurredOn()
        );
        eventHistoryViewRepository.save(eventHistoryView);
    }

    @EventHandler
    public void on(EventEdited event){
        Optional<EventHistoryView> eventHistoryViewOptional = eventHistoryViewRepository.getLastByEventId(event.getEventId());
        if (eventHistoryViewOptional.isPresent()){
            EventHistoryView eventHistoryView = eventHistoryViewOptional.get();
            eventHistoryView = new EventHistoryView(eventHistoryView);

            eventHistoryView.setArtistId(event.getArtistId());
            eventHistoryView.setType(event.getType());
            eventHistoryView.setDescription(event.getDescription());
            eventHistoryView.setDateTime(event.getDateTime());
            eventHistoryView.setLink(event.getLink());
            eventHistoryView.setImage(event.getImage());
            eventHistoryView.setCapacity(event.getCapacity());
            eventHistoryView.setCost(event.getCost());
            eventHistoryView.setCreatedAt(event.getOccurredOn());

            eventHistoryViewRepository.save(eventHistoryView);
        }
    }

    @EventHandler
    public void on(EventDeleted event){
        Optional<EventHistoryView> eventHistoryViewOptional = eventHistoryViewRepository.getLastByEventId(event.getEventId());
        if (eventHistoryViewOptional.isPresent()){
            EventHistoryView eventHistoryView = eventHistoryViewOptional.get();
            eventHistoryView = new EventHistoryView(eventHistoryView);

            eventHistoryView.setEventId(event.getEventId());
            eventHistoryView.setCreatedAt(event.getOccurredOn());

            eventHistoryViewRepository.save(eventHistoryView);
        }
    }
}
