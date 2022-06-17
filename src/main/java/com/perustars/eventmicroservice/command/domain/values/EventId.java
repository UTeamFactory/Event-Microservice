package com.perustars.eventmicroservice.command.domain.values;

import lombok.Value;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Value(staticConstructor = "of")
public class EventId implements Serializable {
    private UUID value;

    private EventId(UUID value){
        this.value = value;
    }

    public EventId(){
        this.value = UUID.randomUUID();
    }

}
