package com.example.eventmicroservice.command.domain.entities;

import com.example.eventmicroservice.command.domain.values.*;
import com.example.eventmicroservice.contracts.commands.EditEvent;
import com.example.eventmicroservice.contracts.commands.RegisterEvent;
import com.example.eventmicroservice.contracts.events.EventDeleted;
import com.example.eventmicroservice.contracts.events.EventEdited;
import com.example.eventmicroservice.contracts.events.EventRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Event {

    @AggregateIdentifier
    private String eventId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "artist_id", length = 100, nullable = false))
    })
    private ArtistId artistId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "type", length = 100, nullable = false))
    })
    private Type type;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "description", length = 200, nullable = false))
    })
    private Description description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "date_time", nullable = false))
    })
    private DateTime dateTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "cost", length = 5, nullable = false))
    })
    private Cost cost;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "image", length = 150, nullable = false))
    })
    private Image image;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "link", length = 150, nullable = false))
    })
    private Link link;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "capacity", length = 150, nullable = false))
    })
    private Capacity capacity;

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
    public void handle(EditEvent command){
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
    public void handle(DeleteEvent command){
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
        artistId = new ArtistId(event.getArtistId());
        type = new Type(event.getType());
        description = new Description(event.getDescription());
        dateTime = new DateTime(event.getDateTime());
        cost = new Cost(event.getCost());
        image = new Image(event.getImage());
        link = new Link(event.getLink());
        capacity = new Capacity(event.getCapacity());
    }

    @EventSourcingHandler
    protected void on (EventEdited event){
        artistId = new ArtistId(event.getArtistId());
        type = new Type(event.getType());
        description = new Description(event.getDescription());
        dateTime = new DateTime(event.getDateTime());
        cost = new Cost(event.getCost());
        image = new Image(event.getImage());
        link = new Link(event.getLink());
        capacity = new Capacity(event.getCapacity());
    }

    @EventSourcingHandler
    protected void on (EventDeleted event){
        eventId = event.getEventId();
    }

}
