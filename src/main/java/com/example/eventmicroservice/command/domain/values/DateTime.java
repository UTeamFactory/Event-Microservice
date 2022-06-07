package com.example.eventmicroservice.command.domain.values;

import com.example.eventmicroservice.common.application.Notification;
import com.example.eventmicroservice.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class DateTime {
    private String value;

    public DateTime(String dateTime) { value = dateTime; }

    protected DateTime() {
        this.value = "";
    }

    public static Result<DateTime, Notification> create(String dateTime) {
        Notification notification = new Notification();
        dateTime = dateTime == null ? "" : dateTime.trim();

        if (dateTime.isEmpty()) {
            notification.addError("DateTime is required", null);
            return Result.failure(notification);
        }
        return  Result.success(new DateTime(dateTime));
    }
}
