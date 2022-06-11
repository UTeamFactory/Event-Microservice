package com.example.eventmicroservice.command.api;

import com.example.eventmicroservice.command.application.dtos.request.CreateEventRequest;
import com.example.eventmicroservice.command.application.dtos.request.DeleteEventRequest;
import com.example.eventmicroservice.command.application.dtos.request.EditEventRequest;
import com.example.eventmicroservice.command.application.dtos.response.CreateEventResponse;
import com.example.eventmicroservice.command.application.dtos.response.DeleteEventResponse;
import com.example.eventmicroservice.command.application.dtos.response.EditEventResponse;
import com.example.eventmicroservice.command.application.services.EventApplicationService;
import com.example.eventmicroservice.command.infrastructure.EventRegistryRepository;
import com.example.eventmicroservice.common.api.ApiController;
import com.example.eventmicroservice.common.application.Notification;
import com.example.eventmicroservice.common.application.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@RestController
@RequestMapping("/events")
@Tag(name = "Events")
public class EventCommandController {
    private final EventApplicationService eventApplicationService;
    private final CommandGateway commandGateway;
    private final EventRegistryRepository eventRegistryRepository;

    public EventCommandController(EventApplicationService eventApplicationService, CommandGateway commandGateway, EventRegistryRepository eventRegistryRepository) {
        this.eventApplicationService = eventApplicationService;
        this.commandGateway = commandGateway;
        this.eventRegistryRepository = eventRegistryRepository;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CreateEventRequest createEventRequest) {
        try {
            Result<CreateEventResponse, Notification> result = eventApplicationService.create(createEventRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Object> edit(@PathVariable("eventId") String eventId, @RequestBody EditEventRequest editEventRequest) {
        try {
            editEventRequest.setEventId(eventId);
            Result<EditEventResponse, Notification> result = eventApplicationService.edit(editEventRequest);
            if (result.isSuccess()) {
                System.out.print("hola1");
                return ApiController.ok(result.getSuccess());
            }
            System.out.print("hola2");
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            System.out.print("error2");
            return ApiController.notFound();
        } catch (Exception e) {
            System.out.print("error3");
            return ApiController.serverError();
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Object> delete(@PathVariable("eventId") String eventId, @RequestBody DeleteEventRequest deleteEventRequest) {
        try {
            deleteEventRequest.setEventId(eventId);
            Result<DeleteEventResponse, Notification> result = eventApplicationService.delete(deleteEventRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
