package com.example.eventmicroservice.query.api;

import com.example.eventmicroservice.query.projections.EventHistoryView;
import com.example.eventmicroservice.query.projections.EventHistoryViewRepository;
import com.example.eventmicroservice.query.projections.EventView;
import com.example.eventmicroservice.query.projections.EventViewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@Tag(name="Events")
public class EventQueryController {

    private final EventViewRepository eventViewRepository;
    private final EventHistoryViewRepository eventHistoryViewRepository;

    public EventQueryController(EventViewRepository eventViewRepository, EventHistoryViewRepository eventHistoryViewRepository) {
        this.eventViewRepository = eventViewRepository;
        this.eventHistoryViewRepository = eventHistoryViewRepository;
    }

    @GetMapping("")
    @Operation(summary = "Get all Event")
    public ResponseEntity<List<EventView>> getAllEvents(){
        try {
            return new ResponseEntity<List<EventView>>(eventViewRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/id/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Event by id")
    public ResponseEntity<EventView> getById(@PathVariable("id") String id){
        try {
            Optional<EventView> eventViewOptional = eventViewRepository.findById(id);
            if (eventViewOptional.isPresent()){
                return new ResponseEntity<EventView>(eventViewOptional.get(),HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CONSUMIR MICROSERVIO DE ARTIST
    @GetMapping("/history/{id}")
    @Operation(summary = "Get Event history")
    public ResponseEntity<List<EventHistoryView>> getHistoryById(@PathVariable("id") String id){
        try {
            List<EventHistoryView> events = eventHistoryViewRepository.getHistoryByEventId(id);
            return new ResponseEntity<List<EventHistoryView>>(events,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
