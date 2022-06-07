package com.example.eventmicroservice.command.domain.values;

import com.example.eventmicroservice.common.application.Notification;
import com.example.eventmicroservice.common.application.Result;

public class ArtistId {
    String value;

    public ArtistId(String value) {
        this.value = value;
    }

    protected ArtistId(){
        value = "";
    }

    public static Result<ArtistId, Notification> create(String artistId) {
        Notification notification = new Notification();
        artistId = artistId == null ? "" : artistId.trim();

        if(artistId.isEmpty()){
            notification.addError("ArtistId is requerid", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new ArtistId(artistId));

    }
}
