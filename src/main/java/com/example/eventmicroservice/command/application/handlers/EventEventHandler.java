package com.example.eventmicroservice.command.application.handlers;

import com.example.eventmicroservice.command.application.infrastructure.EventRegistryRepository;
import org.axonframework.config.ProcessingGroup;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("eventRegistry")
public class EventEventHandler {
    private final EventRegistryRepository eventRegistryRepository;
    public EventEventHandler(EventRegistryRepository eventRegistryRepository){
        this.eventRegistryRepository = eventRegistryRepository;
    }

}
