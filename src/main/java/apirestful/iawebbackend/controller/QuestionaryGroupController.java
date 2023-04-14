package apirestful.iawebbackend.controller;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.services.QuestionaryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class QuestionaryGroupController {
    @Autowired
    private QuestionaryGroupService questionaryGroupService;

    @GetMapping("/all")
    public ResponseEntity<List<QuestionaryGroup>> getQG() throws ResponseStatusException {
        try {
            List<QuestionaryGroup> all = questionaryGroupService.getOrderItemsByOrderId();
            System.out.println(all);
            return new ResponseEntity<List<QuestionaryGroup>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<QuestionaryGroup> all = questionaryGroupService.getOrderItemsByOrderId();
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }


}
