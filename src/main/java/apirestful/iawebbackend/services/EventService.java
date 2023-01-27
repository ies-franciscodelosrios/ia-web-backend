package apirestful.iawebbackend.services;

import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> obtenerEventos(){
        return (List<Event>) eventRepository.findAll();
    }

    public Event guardarEvento(Event evento){
        return eventRepository.save(evento);
    }

    //Para que el método no falle por si no se encuentra el id, le indicamos que es de tipo Optional
    public Optional<Event> obtenerEventoPorId(Long id){
        return eventRepository.findById(id);
    }

    public boolean eliminarEvento(Long id){
        try{
            eventRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }



}
