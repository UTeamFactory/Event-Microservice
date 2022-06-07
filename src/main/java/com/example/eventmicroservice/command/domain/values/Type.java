package com.example.eventmicroservice.command.domain.values;

import com.example.eventmicroservice.common.application.Notification;
import com.example.eventmicroservice.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class Type {
    private String value;

    public Type(String type) { value = type; }

    protected Type() {
        this.value = "";
    }

    public static Result<Type, Notification> create(String type) {
        Notification notification = new Notification();
        type = type == null ? "" : type.trim();

        if(type.isEmpty()){
            notification.addError("type is required", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new Type(type));
    }
}
