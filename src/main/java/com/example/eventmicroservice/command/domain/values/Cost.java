package com.example.eventmicroservice.command.domain.values;

import com.example.eventmicroservice.common.application.Notification;
import com.example.eventmicroservice.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Cost {
    private Double value;

    public Cost(Double cost) {value = cost;}

    protected Cost(){
        this.value = 0.0;
    }

    public static Result<Cost, Notification> create(Double cost) {
        Notification notification = new Notification();

        if(cost < 0.0){
            notification.addError("The cost never can be lower than zero", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }
        return  Result.success(new Cost(cost));

    }
}
