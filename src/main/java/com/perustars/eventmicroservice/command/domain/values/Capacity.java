package com.perustars.eventmicroservice.command.domain.values;

import com.perustars.common.application.Notification;
import com.perustars.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Capacity {

    private Double value;

    public Capacity(Double capacity) {value = capacity;}

    protected Capacity(){
        this.value = 0.0;
    }

    public static Result<Capacity, Notification> create(Double capacity) {
        Notification notification = new Notification();

        if(capacity < 0.0){
            notification.addError("The capacity must be greater than zero. ", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }
        return  Result.success(new Capacity(capacity));

    }
}
