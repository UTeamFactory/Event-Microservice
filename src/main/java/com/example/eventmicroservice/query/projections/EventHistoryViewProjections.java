package com.example.eventmicroservice.query.projections;

import com.perustars.event.contracts.events.EventDeleted;
import com.perustars.event.contracts.events.EventEdited;
import com.perustars.event.contracts.events.EventRegistered;
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
                event.getType(),
                event.getDescription(),
                event.getDateTime(),
                event.getCost(),
                event.getImage(),
                event.getLink(),
                event.getCapacity(),
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

            eventHistoryView.setType(event.getType());
            eventHistoryView.setDescription(event.getDescription());
            eventHistoryView.setDateTime(event.getDateTime());
            eventHistoryView.setCost(event.getCost());
            eventHistoryView.setImage(event.getImage());
            eventHistoryView.setLink(event.getLink());
            eventHistoryView.setCapacity(event.getCapacity());
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
