package com.example.eventmicroservice.command.application.handlers;

import org.axonframework.config.ProcessingGroup;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("artworkRegistry")
public class EventEventHandler {
    private final EventRegistryRepository artworkRegistryRepository;
    public ArtworkEventHandler(ArtworkRegistryRepository artworkRegistryRepository){
        this.artworkRegistryRepository = artworkRegistryRepository;
    }
}
