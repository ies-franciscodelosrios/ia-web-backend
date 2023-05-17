package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.Poll;
import apirestful.iawebbackend.services.PollService;
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

@RestController
@Api(tags = "Poll")
@RequestMapping("/api/poll")

public class PollController {

    @Autowired
    private PollService pollService;

    /**
     * @param pollId
     * @param poll
     * @return a updated poll
     */
    @ApiOperation(value = "update a poll ", notes = "Return updated poll.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping("update")
    public ResponseEntity<Poll> updatePoll(@RequestHeader String paId, @RequestBody Poll poll){
        try {
            if (paId != null) {
                try {
                    return new ResponseEntity<Poll>(pollService.updatePoll(paId, poll), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The poll has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

}
