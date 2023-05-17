package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.services.QuestionaryGroupService;
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
@Api(tags = "QuestionnaireGroup")
@RequestMapping("/api/group")
public class QuestionaryGroupController {
    @Autowired
    private QuestionaryGroupService questionaryGroupService;


    /**
     * @return list of all questionnaires
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get all questionnaires", notes = "List of all questionnaires.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all")
    public ResponseEntity<List<QuestionaryGroup>> getAllQuestionnariesGroups() throws ResponseStatusException {
        try {
            List<QuestionaryGroup> all = questionaryGroupService.getAllQuestionnariesGroups();
            return new ResponseEntity<List<QuestionaryGroup>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by data", e);
        }
    }



    /**
     * @return List of inactivates questionnaires
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "List of inactivates questionnaires", notes = "Get all inactivates questionnaires")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/inactivates")
    public ResponseEntity<List<QuestionaryGroup>> getAllInactiveQuestionaryGroup() throws ResponseStatusException {
        try {
            List<QuestionaryGroup> all = questionaryGroupService.getAllInactiveQuestionaryGroup();
            return new ResponseEntity<List<QuestionaryGroup>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by data", e);
        }
    }



    /**
     * @param qgId
     * @return a questionnaire group by Id
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Questionnaire by Id", notes = "Get questionnaire by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/ById")
    public ResponseEntity<QuestionaryGroup> getQuestionnarieGroupsById(@RequestHeader String qgId) throws ResponseStatusException {
        try {
            if (!qgId.isEmpty()) {
                try {
                    QuestionaryGroup questionaryGroup = questionaryGroupService.getQuestionaryGroupbyID(qgId);
                    return new ResponseEntity<QuestionaryGroup>(questionaryGroup, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The QuestionnaireGroup with id: " + qgId + " has not found");
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission", e);
        }
    }



    /**
     * @param idNavision
     * @return Questionnaires list of one user.
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get a questionnarie by user", notes = "Questionnaires list of one user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/ByUser")
    public ResponseEntity<List<QuestionaryGroup>> getAllQuestionnariesGroupsByUser(@RequestHeader String idNavision) throws ResponseStatusException {
        try {
            if (!idNavision.isEmpty()) {
                try {
                    List<QuestionaryGroup> all = questionaryGroupService.getQuestionaryGroupsByUser(idNavision);
                    return new ResponseEntity<List<QuestionaryGroup>>(all, new HttpHeaders(), HttpStatus.OK);
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
     * @param questionaryGroup
     * @return the QuestionnaireGroup recently created.
     */
    @ApiOperation(value = "Create a questionaryGroup", notes = "Return a questionaryGroup recently created.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping("create")
    public ResponseEntity<?> createQuestionary(@RequestBody QuestionaryGroup questionaryGroup) {
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


    /**
     * @param qgId
     * @param questionaryGroup
     * @return a updated questionnaire group
     */
    @ApiOperation(value = "updated questionnaire group", notes = "Return a updated questionnaire group")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping("update")
    public ResponseEntity<QuestionaryGroup> updateQuestionnaire(@RequestHeader String qgId, @RequestBody QuestionaryGroup questionaryGroup){
        try {
            if (qgId != null) {
                try {
                    return new ResponseEntity<QuestionaryGroup>(questionaryGroupService.updateQuestionnaire(qgId, questionaryGroup), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The questionnaire has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }


}
