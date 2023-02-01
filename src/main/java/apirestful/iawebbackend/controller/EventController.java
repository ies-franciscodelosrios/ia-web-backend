package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.model.Turn;
import apirestful.iawebbackend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping()
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

    @GetMapping(path ="/user/{userId}")
    public ResponseEntity<Set<Event>> getUserEvents(@PathVariable String userId) {
        try {
            return new ResponseEntity<Set<Event>>(eventService.getUserEvents(userId), new HttpHeaders(), HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un usuario con ese identificador");
        }
    }
    @PostMapping(path = "/save/assignUser/{userId}")
    public Event saveEvent(@RequestBody Event evento, @PathVariable String userId ){
        return this.eventService.saveEvent(userId,evento);
    }


    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Event> editEvent(@PathVariable Long id, @RequestBody Event evento){
        return this.eventService.updateEvent(id,evento);
    }


    @GetMapping(path = "/{id}")
    public Optional<Event> getEventById(@PathVariable("id") Long id){
        return this.eventService.getEventById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteEvent(@PathVariable("id") Long id){
        return this.eventService.deleteEvent(id);
    }

    @DeleteMapping(path = "/delete/user/{userId}")
    public boolean deleteEventByUser(@PathVariable String userId){
            return eventService.deleteUserEvents(userId);

    }


}
