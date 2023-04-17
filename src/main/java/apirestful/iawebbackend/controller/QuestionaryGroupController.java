package apirestful.iawebbackend.controller;
import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.services.QuestionaryGroupService;
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


    /**
     * @param questionaryGroup
     * @return Create a user with a data
     */
    @ApiOperation(value = "Create a questionaryGroup with a data", notes = "Return a questionaryGroup with a data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody QuestionaryGroup questionaryGroup) {
        try {
            if (questionaryGroup != null) {
                try {
                    QuestionaryGroup QGCreate = questionaryGroupService.createQuestionaryGroup(questionaryGroup);
                    return new ResponseEntity<QuestionaryGroup>(QGCreate, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The questionaryGroup has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }

    }


}
