package apirestful.iawebbackend.services;

import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.model.Turn;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.EventRepository;
import apirestful.iawebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> getEvents(){
        return (List<Event>) eventRepository.findAll();
    }

    public Set<Event> getUserEvents(String userId) {
        User user = userRepository.findById(userId).get();
        return user.getEvents();
    }



    public Event saveEvent(String userId, Event evento){
        User user = userRepository.findById(userId).get();
        evento.setUser(user);
        return eventRepository.save(evento);
    }
    public ResponseEntity<Event> updateEvent(Long id, Event evento){
        Optional<Event> optionalEvento = eventRepository.findById(id);
        if(!optionalEvento.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        evento.setId(optionalEvento.get().getId());
        evento.setUser(optionalEvento.get().getUser());
        eventRepository.save(evento);
        return ResponseEntity.ok().build();
    }
    public Optional<Event> getEventById(Long id){
        return eventRepository.findById(id);
    }

    public boolean deleteEvent(Long id){
        try{
            eventRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public boolean deleteUserEvents(String userId) {
        try {
            User user = userRepository.getById(userId);
          for (Event event:user.getEvents()){
              event.setUser(null);
              eventRepository.delete(event);
          }
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
