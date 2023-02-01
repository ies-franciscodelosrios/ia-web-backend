package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping()
    public List<Event> getEvents(){
        return eventService.getEvents();
    }



    @PostMapping(path = "/save/assignUser/{userid}")
    public Event saveEvent(@RequestBody Event evento, @PathVariable String userid ){
        return this.eventService.saveEvent(userid,evento);
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

}
