package apirestful.iawebbackend.controller;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.Response;
import apirestful.iawebbackend.services.ResponseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/response")
public class ResponseController {

    @Autowired
    private ResponseService responseService;
    @GetMapping("/all")
    public ResponseEntity<Optional<Response>> getQG() throws ResponseStatusException {
        try {
            Optional<Response> all = responseService.getAllResponses();
            System.out.println(all);
            return new ResponseEntity<Optional<Response>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            Optional<Response> all = responseService.getAllResponses();
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }


    /**
     * @param response
     * @return Create a question with a data
     */
    @ApiOperation(value = "Create a question with a data", notes = "Return a question with a data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping
    public void createQuestion(@RequestBody Response response, @RequestHeader int pa_id) {
        try {
            if (response != null) {
                try {
                    responseService.createResponse(response, pa_id);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The response has not found");
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission", e);
        }
    }

}
