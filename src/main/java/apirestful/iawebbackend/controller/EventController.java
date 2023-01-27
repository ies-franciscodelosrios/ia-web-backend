package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping()
    public List<Event> obtenerEventos(){
        return eventService.obtenerEventos();
    }


    //Sirve para guardar y actualizar eventos
    @PostMapping()
    public Event guardarEvento(@RequestBody Event evento){
        return this.eventService.guardarEvento(evento);
    }

    @GetMapping(path = "/{id}")
    public Optional<Event> obtenerEventoPorId(@PathVariable("id") Long id){
        return this.eventService.obtenerEventoPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public boolean eliminarEvento(@PathVariable("id") Long id){
        return this.eventService.eliminarEvento(id);
    }

}
