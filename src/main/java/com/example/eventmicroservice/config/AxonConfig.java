package com.example.eventmicroservice.config;

import com.example.eventmicroservice.command.domain.entities.Event;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public Repository<Event> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Event.class)
                .eventStore(eventStore)
                .build();
    }
}
