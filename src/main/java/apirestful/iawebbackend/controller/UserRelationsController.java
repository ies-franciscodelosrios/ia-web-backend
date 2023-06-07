package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.model.UserRelationsPK;
import apirestful.iawebbackend.model.UsersRelations;
import apirestful.iawebbackend.services.UserRelationsService;
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
@RequestMapping("/api/userRelations")
public class UserRelationsController {
    @Autowired
    private UserRelationsService userRelationsService;


    /**
     * @return a list of all User-Relations
     */
    @ApiOperation(value = "Get a all User-Relations", notes = "Return a list of all User-Relations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all")
    public ResponseEntity<List<UsersRelations>> getAllUsersRelations() {
        try {
            List<UsersRelations> all = userRelationsService.getAllUserRelations();
            return new ResponseEntity<List<UsersRelations>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<UsersRelations> all = userRelationsService.getAllUserRelations();
            return new ResponseEntity<List<UsersRelations>>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }


    /**
     * @param id1
     * @param id2
     * @return a User-Relation updated
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Update a User-Relation", notes = "Return a User-Relation updated")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping("/{id1}/{id2}")
    public ResponseEntity<UsersRelations> updateUserRelation(@PathVariable String id1, @PathVariable String id2) throws ResponseStatusException {
        try {
            if (id1 != null) {
                try {
                    UserRelationsPK id= new UserRelationsPK();
                    id.setIdNavision(id1);
                    id.setIdNavision2(id2);
                    UsersRelations ur= userRelationsService.UpdateUserRelation(id);
                    return new ResponseEntity<UsersRelations>(ur, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data "+e);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The User-Relation has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }


    }


    /**
     * @param id1
     * @param id2
     * @return a User-Relation between two persons
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get a User-Relation between two persons", notes = "Return a User-Relation between two persons")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping
    public ResponseEntity<UsersRelations> getUserRelationbyIDNavision(@RequestHeader String id1, @RequestHeader String id2) throws ResponseStatusException {
        try {
            if (id1 != null) {
                try {
                    UserRelationsPK id= new UserRelationsPK();
                    id.setIdNavision(id1);
                    id.setIdNavision2(id2);
                    UsersRelations ur= userRelationsService.getUserByIdNavision(id);
                    return new ResponseEntity<UsersRelations>(ur, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data "+e);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The User-Relation has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }


    }




    /**
     * @param idnavision
     * @return a User-Relation active by IdNavision
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get a User-Relation active by IdNavision", notes = "Return a User-Relation active by IdNavision")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/active")
    public ResponseEntity<List<String>> getUserRelationActivebyIDNavision(@RequestHeader String idnavision) throws ResponseStatusException {
        try {
            if (idnavision != null) {
                try {
                    UserRelationsPK id= new UserRelationsPK();
                    id.setIdNavision(idnavision);
                    id.setIdNavision2("");
                    List<String> ur= userRelationsService.getNameActiveRelationsByUser(id.getIdNavision());
                    return new ResponseEntity<List<String>>(ur, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data "+e);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The User-Relation has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }


    /**
     * @param userelation
     * @return a User-Relation created
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Create a User-Relation", notes = "Return a User-Relation created")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping("/create")
    public ResponseEntity<UsersRelations> createUserRelation(@RequestBody UsersRelations userelation) throws ResponseStatusException {
        try {
            if (userelation != null) {
                try {
                    UsersRelations kid = userRelationsService.createUserRelation(userelation);
                    return new ResponseEntity<UsersRelations>(kid, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data " + e);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The User-Relation has not found");
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission", e);
        }
    }
}
