package apirestful.iawebbackend.controller;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.services.QuestionService;
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

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @ApiOperation(value = "Get all questions", notes = "Return list of all questions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() throws ResponseStatusException {
        try {
            List<Question> all = questionService.getAllQuestions();
            System.out.println(all);
            return new ResponseEntity<List<Question>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<Question> all = questionService.getAllQuestions();
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }

    /**
     * @param question
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
    public ResponseEntity<?> createQuestion(@RequestBody Question question) {
        try {
            if (question != null) {
                try {
                    Question QGCreate = questionService.createQuestion(question);
                    return new ResponseEntity<Question>(QGCreate, new HttpHeaders(), HttpStatus.OK);
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



    /**
     * @param question
     * @return Update a user with a data
     */
    @ApiOperation(value = "Update a question with a data", notes = "Return a question with a data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping
    public ResponseEntity<?> updateQuestion(@RequestBody Question question) {
        try {
            if (question != null) {
                try {
                    Question QGCreate = questionService.updateQuestion(question);
                    return new ResponseEntity<Question>(QGCreate, new HttpHeaders(), HttpStatus.OK);
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



    /**
     * @param question_id
     * @return Delete a question by ID of a Question
     */
    @ApiOperation(value = "Delete a question by ID of a Question", notes = "Return a deleted question")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @DeleteMapping
    public HttpStatus deleteQuestion(@RequestHeader Long question_id) {
        try {
            if (question_id != null) {
                try {
                    questionService.deleteQuestionByID(question_id);
                    return HttpStatus.OK;
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
