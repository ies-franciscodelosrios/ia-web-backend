package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.services.UserRelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userRelations")
public class UserRelationsController {
    @Autowired
    private UserRelationsService userRelationsService;
}
