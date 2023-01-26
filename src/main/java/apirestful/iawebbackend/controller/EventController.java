package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;
}
