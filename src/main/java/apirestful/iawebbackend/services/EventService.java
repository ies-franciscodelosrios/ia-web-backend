package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.EventRepository;
import apirestful.iawebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Gets all the events from the database
     * @return all the events from the database
     */
    public List<Event> getEvents(){
        return (List<Event>) eventRepository.findAll();
    }

    /**
     * Gets all the events of a user
     * @param userId
     * @return the event of that user
     */
    public Set<Event> getUserEvents(String userId) {
        try {
            if (userRepository.existsById(userId)) {
                User user = userRepository.findById(userId).get();
                return user.getEvents();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The user with userId "+userId+" not exist");
            }
        }catch (RecordNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not exists with this userId",ex);
        }
    }

    /**
     * Creates an event for a user
     * @param userId
     * @param evento
     * @return The event created for that user
     */
    public Event saveEvent(String userId, Event evento) {
        try {
            User user = userRepository.findById(userId).get();
            evento.setUser(user);
            return eventRepository.save(evento);
        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists with userId "+userId, ex);
        }
    }

    /**
     * Updates an event by id
     * @param id
     * @param evento
     * @return The event updated
     */
    public ResponseEntity<Event> updateEvent(Long id, Event evento){
        Optional<Event> optionalEvento = eventRepository.findById(id);
        try {
            if (!optionalEvento.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            evento.setId(optionalEvento.get().getId());
            evento.setUser(optionalEvento.get().getUser());
            eventRepository.save(evento);
            return ResponseEntity.ok().build();
        }catch (RecordNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The request was not processed correctly",ex);
        }

    }

    /**
     * Gets an event by id
     * @param id
     * @return an event
     */
    public Optional<Event> getEventById(Long id){
        try {
            if (id != null) {
                return eventRepository.findById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The event with id " + id + " not exist");
            }
        }catch (RecordNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The request was not processed correctly",ex);
        }
    }

    /**
     * Deletes an event by its id
     * @param id
     * @return true if deleted or false if it could not be deleted.
     */
    public boolean deleteEvent(Long id){
        try{
            Optional<Event> event = eventRepository.findById(id);
            if(event.isPresent()) {
                eventRepository.deleteById(id);
                return true;
            }else {
                return false;
            }
        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The request was not processed correctly",ex);
        }
    }

    /**
     * Deletes all events of a user
     * @param userId
     * @return true if deleted or false if it could not be deleted.
     */
    public boolean deleteUserEvents(String userId) {
        try {
            User user = userRepository.getById(userId);
            if(user != null) {
                for (Event event : user.getEvents()) {
                    event.setUser(null);
                    eventRepository.delete(event);
                }
                return true;
            }else{
                return false;
            }

        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The request was not processed correctly",ex);
        }
    }

    /**
     * Deletes a user event
     * @param userId
     * @param eventId
     * @return true if deleted or false if it could not be deleted.
     */
    public boolean deleteUserEvent(String userId, Long eventId) {
        if (userRepository.getById(userId) != null && eventRepository.findById(eventId) != null) {
            try {
                User user = userRepository.getById(userId);
                for (Event event : user.getEvents()) {
                    if(event.getId() == eventId) {
                        eventRepository.deleteById(eventId);
                        return true;
                    }
                }
            } catch (RecordNotFoundException ex) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "The request was not processed correctly",ex);
            }
        }
        return false;
    }
}
