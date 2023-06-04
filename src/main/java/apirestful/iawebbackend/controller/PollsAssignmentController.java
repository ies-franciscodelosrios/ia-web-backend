package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.PollsAssignment;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.services.PollsAssignmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Api(tags = "PollAssignment")
@RequestMapping("/api/pollAssignments")
public class PollsAssignmentController {
    @Autowired
    private PollsAssignmentService pollsAssignmentService;

    /**
     * @return list of all polls assignments
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get all polls assignments", notes = "List of all polls assignments.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all")
    public ResponseEntity<List<PollsAssignment>> getQG() throws ResponseStatusException {
        try {
            List<PollsAssignment> all = pollsAssignmentService.getAllPA();
            return new ResponseEntity<List<PollsAssignment>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<PollsAssignment> all = pollsAssignmentService.getAllPA();
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/allingPA")
    public ResponseEntity<PollsAssignment> getPAID() throws ResponseStatusException {
        try {
            PollsAssignment all = pollsAssignmentService.getPAbyID("2");
            return new ResponseEntity<PollsAssignment>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            PollsAssignment all = pollsAssignmentService.getPAbyID("2");
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }


    /**
     * @param idNavision
     * @return a list of pollsAssignments of one user
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get all polls assignments by User", notes = "List of all polls assignments by User.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/ByUser")
    public ResponseEntity<List<PollsAssignment>> getAllPollAssignmentsByUser(@RequestHeader String idNavision) throws ResponseStatusException {
        try {
            if (!idNavision.isEmpty()) {
                try {
                    List<PollsAssignment> all = pollsAssignmentService.getPollsAssignmentsByUser(idNavision);
                    return new ResponseEntity<List<PollsAssignment>>(all, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The User with idNavision: " + idNavision + " has not found");
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission", e);
        }
    }

    /**
     *
     * @param pollsAssignment
     * @param QGname
     * @return the poll assignment recently created or updated
     */
    @ApiOperation(value = "Create or update a poll assignment", notes = "Return a poll assignment recently created or updated.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping("/save/assignQG")
    public ResponseEntity<PollsAssignment> createTurn(@RequestBody PollsAssignment pollsAssignment, @RequestHeader String QGname) {
        try {
            if (pollsAssignment != null) {
                try {
                    return new ResponseEntity<PollsAssignment>(pollsAssignmentService.createPollAssignment(pollsAssignment, Long.valueOf(QGname)), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The questionnaire group has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }
}
