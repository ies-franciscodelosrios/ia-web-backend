package apirestful.iawebbackend.controller;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.model.Response;
import apirestful.iawebbackend.model.TextRelation;
import apirestful.iawebbackend.services.ResponseService;
import apirestful.iawebbackend.services.TextRelationService;
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
import java.util.Map;

@RestController
@RequestMapping("/tr")
public class TextRelationController {


    @Autowired
    private TextRelationService textRelationService;


    @ApiOperation(value = "Get All Text Relations", notes = "Return a list of all Text Relation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all")
    public ResponseEntity<List<TextRelation>> getQG() throws ResponseStatusException {
        try {
            List<TextRelation> all = textRelationService.getAlltr();
            System.out.println(all);
            return new ResponseEntity<List<TextRelation>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<TextRelation> all = textRelationService.getAlltr();
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Get all Questions of a one Survey", notes = "Return a list of all questions of one Survey")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all/questions")
    public ResponseEntity<List<Question>> getAllQuestionsBySurvey(@RequestHeader Long id_qg) throws ResponseStatusException {

        try {
            List<Question> all = textRelationService.getAllQuestionsBySurvey(id_qg);
            System.out.println(all);
            return new ResponseEntity<List<Question>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<Question> all = textRelationService.getAllQuestionsBySurvey(id_qg);
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Get all Response of a one Survey by one User", notes = "Return a list of all responses of one Survey by one User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all/questions/user")
    public ResponseEntity<List<Map<Question,Response>>> getAllResponsesByUserQG(@RequestHeader Long id_qg, @RequestHeader String id_navision) throws ResponseStatusException {

        try {
            List<Map<Question,Response>> all = textRelationService.getAllResponsesByUserSurvey(id_qg,id_navision);
            System.out.println(all);
            return new ResponseEntity<List<Map<Question,Response>>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<Map<Question,Response>> all = textRelationService.getAllResponsesByUserSurvey(id_qg,id_navision);
            return new ResponseEntity<List<Map<Question,Response>>>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }



    /**
     * @param textRelation
     * @return Create a question with a data
     */
    @ApiOperation(value = "Create a Text-Relation with a data", notes = "Return a text-relation with a data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping
    public ResponseEntity<?> createTR(@RequestBody TextRelation textRelation) {
        try {
            if (textRelation != null) {
                try {
                    TextRelation QGCreate = textRelationService.createTR(textRelation);
                    return new ResponseEntity<TextRelation>(QGCreate, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The response has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }

    }
}
