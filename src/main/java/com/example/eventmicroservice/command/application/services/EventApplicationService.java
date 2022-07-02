package com.example.eventmicroservice.command.application.services;

import com.example.eventmicroservice.command.application.dtos.request.CreateEventRequest;
import com.example.eventmicroservice.command.application.dtos.request.DeleteEventRequest;
import com.example.eventmicroservice.command.application.dtos.request.EditEventRequest;
import com.example.eventmicroservice.command.application.dtos.response.CreateEventResponse;
import com.example.eventmicroservice.command.application.dtos.response.DeleteEventResponse;
import com.example.eventmicroservice.command.application.dtos.response.EditEventResponse;
import com.example.eventmicroservice.command.application.validators.CreateEventValidator;
import com.example.eventmicroservice.command.application.validators.DeleteEventValidator;
import com.example.eventmicroservice.command.application.validators.EditEventValidator;
import com.example.eventmicroservice.command.infrastructure.EventRegistryRepository;
import com.example.eventmicroservice.common.application.Notification;
import com.example.eventmicroservice.common.application.Result;
import com.example.eventmicroservice.common.application.ResultType;
import com.perustars.event.contracts.commands.DeleteEvent;
import com.perustars.event.contracts.commands.EditEvent;
import com.perustars.event.contracts.commands.RegisterEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class EventApplicationService {
    private final CreateEventValidator createEventValidator;
    private final EditEventValidator editEventValidator;
    private final DeleteEventValidator deleteEventValidator;
    protected final CommandGateway commandGateway;
    private final EventRegistryRepository eventRegistryRepository;

    public EventApplicationService(CreateEventValidator createEventValidator, EditEventValidator editEventValidator, DeleteEventValidator deleteEventValidator, CommandGateway commandGateway, EventRegistryRepository eventRegistryRepository) {
        this.createEventValidator = createEventValidator;
        this.editEventValidator = editEventValidator;
        this.deleteEventValidator = deleteEventValidator;
        this.commandGateway = commandGateway;
        this.eventRegistryRepository = eventRegistryRepository;
    }

    public Result<CreateEventResponse, Notification> create(CreateEventRequest createEventRequest) throws Exception {
        Notification notification = this.createEventValidator.validate(createEventRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String eventId = UUID.randomUUID().toString();
        RegisterEvent createEvent = new RegisterEvent(
                eventId,
                createEventRequest.getArtistId().trim(),
                createEventRequest.getType().trim(),
                createEventRequest.getDescription().trim(),
                createEventRequest.getDateTime(),
                createEventRequest.getCost(),
                createEventRequest.getImage().trim(),
                createEventRequest.getLink().trim(),
                createEventRequest.getCapacity()
                );
        CompletableFuture<Object> future = commandGateway.send(createEvent);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        CreateEventResponse createEventResponseDto = new CreateEventResponse(
                createEvent.getEventId(),
                createEvent.getArtistId(),
                createEvent.getType(),
                createEvent.getDescription(),
                createEvent.getDateTime(),
                createEvent.getCost(),
                createEvent.getImage(),
                createEvent.getLink(),
                createEvent.getCapacity()
        );
        return Result.success(createEventResponseDto);
    }

    public Result<EditEventResponse, Notification> edit(EditEventRequest editEventRequest) throws Exception {
        Notification notification = this.editEventValidator.validate(editEventRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditEvent editEvent = new EditEvent(
                editEventRequest.getEventId().trim(),
                editEventRequest.getArtistId().trim(),
                editEventRequest.getType().trim(),
                editEventRequest.getDescription().trim(),
                editEventRequest.getDateTime(),
                editEventRequest.getCost(),
                editEventRequest.getImage().trim(),
                editEventRequest.getLink().trim(),
                editEventRequest.getCapacity()
                );

        CompletableFuture<Object> future = commandGateway.send(editEvent);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditEventResponse editEventResponseDto = new EditEventResponse(
                editEvent.getEventId(),
                editEvent.getArtistId(),
                editEvent.getType(),
                editEvent.getDescription(),
                editEvent.getDateTime(),
                editEvent.getCost(),
                editEvent.getImage(),
                editEvent.getLink(),
                editEvent.getCapacity()
        );
        return Result.success(editEventResponseDto);
    }

    public Result<DeleteEventResponse, Notification> delete(DeleteEventRequest deleteEventRequest) throws Exception {
        Notification notification = this.deleteEventValidator.validate(deleteEventRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        DeleteEvent deleteEvent = new DeleteEvent(
                deleteEventRequest.getEventId().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(deleteEvent);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) ->  (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        DeleteEventResponse deleteEventResponse = new DeleteEventResponse(
                deleteEvent.getEventId()
        );
        return Result.success(deleteEventResponse);
    }

}
