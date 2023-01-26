package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/turn")
public class TurnController {
    @Autowired
    private TurnService turnService;
}
