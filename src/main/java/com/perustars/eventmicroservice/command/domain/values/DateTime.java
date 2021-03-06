package com.perustars.eventmicroservice.command.domain.values;

import com.perustars.common.application.Notification;
import com.perustars.common.application.Result;
import lombok.Value;

import javax.persistence.Embeddable;
import java.util.Calendar;

@Embeddable
@Value
public class DateTime {
    private Calendar value;

    public DateTime(Calendar dateTime) { value = dateTime; }
    Calendar now = Calendar.getInstance();
    protected DateTime() {
        this.value = now;
    }

    public static Result<DateTime, Notification> create(Calendar dateTime) {
        Notification notification = new Notification();
        return  Result.success(new DateTime(dateTime));
    }
}
