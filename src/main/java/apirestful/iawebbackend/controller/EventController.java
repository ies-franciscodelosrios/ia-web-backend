package apirestful.iawebbackend.controller;
import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.security.UserDetailServiceImpl;
import apirestful.iawebbackend.services.EventService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/event")
@Api( tags = "Event")
public class EventController {
    @Autowired
    private EventService eventService;
    
    @Autowired
    private UserDetailServiceImpl userDetailService;

    /**
     * Show all turns in db
     * @return Get all turns
     */
    @ApiOperation(value = "Get all events", notes = "Returns all events from db")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getEvents(HttpServletRequest request){
        String token = null;
        token = request.getHeader("Authorization");
        try{
            if (token == null || !token.startsWith("Bearer")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }else{
                return new ResponseEntity<List<Event>>(this.eventService.getEvents(),new HttpHeaders(),HttpStatus.OK);
            }
        }catch(RecordNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Authentication failed",ex);
        }
    }

    /**
     * @param userId
     * @return Get all events of a user
     */
    @ApiOperation(value = "Get all the events of a user", notes = "Events of a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping(path ="/user/")
    public ResponseEntity<Set<Event>> getUserEvents(@RequestHeader String userId){
        try {
            if (eventService.getUserEvents(userId).size() >=0) {
                return new ResponseEntity<Set<Event>>(eventService.getUserEvents(userId), new HttpHeaders(), HttpStatus.OK);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not exists with this userId "+userId);
            }
        }catch (RecordNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not exists with this userId",ex);
        }
    }

    /**
     * @param evento
     * @param userId
     * @return an event of a user
     */
    @ApiOperation(value = "Create events for a user", notes = "The user must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping(path = "/save/assignUser/")
    public ResponseEntity<Event> saveEvent(@RequestBody Event evento, @RequestHeader String userId ){
        try {
            if (userId != null || eventService.getUserEvents(userId).size() >0) {
                return new ResponseEntity<Event>(this.eventService.saveEvent(userId, evento), new HttpHeaders(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (RecordNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists with this userId",ex);
        }
    }

    /**
     * Edit an event
     * @param id
     * @param evento
     * @return an edited event
     */
    @ApiOperation(value = "Edit event for a user", notes = "The event must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping(path = "/update/")
    public ResponseEntity<Event> editEvent(@RequestHeader Long id, @RequestBody Event evento){
        try {
            if (evento !=null && this.eventService.getEventById(id).isPresent()) {
                return new ResponseEntity<Event>(this.eventService.updateEvent(id, evento).getBody(),new HttpHeaders(),HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No event exist with id "+id);
            }
        }catch (RecordNotFoundException exc){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The request was not processed correctly",exc);
        }
    }

    /**
     * @param id
     * @return Get an event by id
     */
    @ApiOperation(value = "Get an event by id", notes = "The event must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping(path = "/")
    public ResponseEntity<Optional<Event>> getEventById(@RequestHeader Long id){
        try {
            if (this.eventService.getEventById(id).isPresent()) {
                return new ResponseEntity<Optional<Event>>(this.eventService.getEventById(id), HttpStatus.OK);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The event with id "+id+" not exist");
            }
        }catch (RecordNotFoundException exc){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The event with id "+id+" not exist",exc);
        }
    }

    /**
     * @param id
     * @return code 200 if the event was deleted
     */
    @ApiOperation(value = "Delete an event by id", notes = "The event must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @DeleteMapping(path = "/delete/")
    public ResponseEntity deleteEvent(@RequestHeader Long id){
        try{
            if(this.eventService.getEventById(id).isPresent()) {
                return new ResponseEntity(this.eventService.deleteEvent(id),HttpStatus.OK);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The event with id "+id+" not exist");
            }
        }catch (RecordNotFoundException exc){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The event with id "+id+" not exist",exc);
        }
    }

    /**
     * @param userId
     * @return code 200 if the all events was deleted
     */
    @ApiOperation(value = "Deletes all events of a user", notes = "The user must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @DeleteMapping(path = "/delete/user")
    public ResponseEntity deleteEventsByUser(@RequestHeader String userId){
        try {
            if (this.eventService.deleteUserEvents(userId)) {
                return new ResponseEntity("Message: All user events with userId "+userId+" were deleted",HttpStatus.OK);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The user with userId "+userId+" not exist");
            }
        }catch (RecordNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists with this userId",ex);
        }
    }
    @ApiOperation(value = "Deletes an event of a user", notes = "The user and event must exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @DeleteMapping(path = "/delete/user/")
    public ResponseEntity deleteEventByUser(@RequestHeader String userId, @RequestHeader Long eventId){
        try {
            if(eventService.deleteUserEvent(userId,eventId)){
                return new ResponseEntity("Message : The event with id "+eventId+" of user "+userId+" was deleted ",HttpStatus.OK);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The user or event not exist");
            }
        }
        catch (RecordNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user or event not exist",ex);
        }
    }
}