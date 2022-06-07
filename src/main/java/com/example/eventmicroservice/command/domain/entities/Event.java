package com.example.eventmicroservice.command.domain.entities;

import com.example.eventmicroservice.contracts.commands.DeleteEvent;
import com.example.eventmicroservice.contracts.commands.EditEvent;
import com.example.eventmicroservice.contracts.commands.RegisterEvent;
import com.example.eventmicroservice.contracts.events.EventDeleted;
import com.example.eventmicroservice.contracts.events.EventEdited;
import com.example.eventmicroservice.contracts.events.EventRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.util.Calendar;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Event {

    @AggregateIdentifier
    private String eventId;
    private String artistId;
    private String type;
    private String description;
    private Calendar dateTime;
    private String cost;
    private String image;
    private String link;
    private String capacity;

    public Event(){}

    @CommandHandler
    public Event(RegisterEvent command){
        Instant now = Instant.now();
        apply(
                new EventRegistered(
                        command.getEventId(),
                        command.getArtistId(),
                        command.getType(),
                        command.getDescription(),
                        command.getDateTime(),
                        command.getCost(),
                        command.getImage(),
                        command.getLink(),
                        command.getCapacity(),
                        now
                )
        );
    }

    @CommandHandler
    public Event(EditEvent command){
        Instant now = Instant.now();
        apply(
                new EventEdited(
                        command.getEventId(),
                        command.getArtistId(),
                        command.getType(),
                        command.getDescription(),
                        command.getDateTime(),
                        command.getCost(),
                        command.getImage(),
                        command.getLink(),
                        command.getCapacity(),
                        now
                )
        );
    }

    @CommandHandler
    public Event(DeleteEvent command){
        Instant now = Instant.now();
        apply(
                new EventDeleted(
                        command.getEventId(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void on (EventRegistered event){
        eventId = event.getEventId();
        artistId = event.getArtistId();
        type = event.getType();
        description = event.getDescription();
        dateTime = event.getDateTime();
        cost = event.getCost();
        image = event.getImage();
        link = event.getLink();
        capacity = event.getCapacity();
    }

    @EventSourcingHandler
    protected void on (EventEdited event){
        artistId = event.getArtistId();
        type = event.getType();
        description = event.getDescription();
        dateTime = event.getDateTime();
        cost = event.getCost();
        image = event.getImage();
        link = event.getLink();
        capacity = event.getCapacity();
    }

    @EventSourcingHandler
    protected void on (EventDeleted event){
        eventId = event.getEventId();
    }

}
