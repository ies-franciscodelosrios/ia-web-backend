package apirestful.iawebbackend.controller;
import apirestful.iawebbackend.model.PollsAssignment;
import apirestful.iawebbackend.services.PollsAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/pollassignments")
public class PollsAssignmentController {
    @Autowired
    private PollsAssignmentService pollsAssignmentService;

    @GetMapping("/all")
    public ResponseEntity<List<PollsAssignment>> getQG() throws ResponseStatusException {
        try {
            List<PollsAssignment> all = pollsAssignmentService.getAllPA();
            System.out.println(all);
            return new ResponseEntity<List<PollsAssignment>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<PollsAssignment> all = pollsAssignmentService.getAllPA();
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }
}
