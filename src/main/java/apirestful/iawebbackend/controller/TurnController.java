package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.Turn;
import apirestful.iawebbackend.services.TurnService;
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
import java.util.Set;

@RestController
@Api(tags = "Turn")
@RequestMapping("/api/turn")
public class TurnController {
    @Autowired
    private TurnService turnService;

    /**
     * @return a turns list
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get all turns", notes = "Returns a turns list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping(value = {"/getAllTurns"})
    public ResponseEntity<List<Turn>> getAllTurns() {
        try {
            return new ResponseEntity<List<Turn>>(turnService.getAllTurns(), new HttpHeaders(), HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La petición no se ha realizado correctamente");
        }
    }

    /**
     * @param turnId
     * @return Get a turn by turnId
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get a turn by turnId", notes = "Return a simple turn")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/getTurn")
    public ResponseEntity<Turn> getTurnById(@RequestHeader String turnId) {
        try{
            if (turnId != null){
                Turn turn = turnService.getTurnById(turnId);
                if (turn != null) {
                    return new ResponseEntity<>(turn, HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The turn has not found");
                }
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
            }
        }catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission", e);
        }

    }

    /**
     * @param IdNavision
     * @return Get turn list of one user by IDNavision
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get user turns", notes = "Return a turn list of one user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/getUserTurns")
    public ResponseEntity<Set<Turn>> getUserTurns(@RequestHeader String IdNavision) {
        try {
            return new ResponseEntity<Set<Turn>>(turnService.getUserTurns(IdNavision), new HttpHeaders(), HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un usuario con ese identificador");
        }
    }

    /**
     * @param turn
     * @param IdNavision
     * @return the created turn
     */
    @ApiOperation(value = "Create and assign turn to current user", notes = "Return the created turn")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping("/save/assignUser")
    public ResponseEntity<Turn> createTurn(@RequestBody Turn turn, @RequestHeader String IdNavision) {
        try {
            if (turn != null) {
                try {
                    return new ResponseEntity<Turn>(turnService.createTurn(turn, IdNavision), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The turn has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

    /**
     * @param turnId
     * @param turn
     * @return updated turn
     */
    @ApiOperation(value = "Update a user with a data", notes = "Return a updated turn")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping("update")
    public ResponseEntity<Turn> updateTurn(@RequestHeader String turnId, @RequestBody Turn turn){
        try {
            if (turn != null) {
                try {
                    return new ResponseEntity<Turn>(turnService.updateTurn(turn, turnId).getBody(), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The turn has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

    @ApiOperation(value = "Delete a turn by turnId", notes = "Return a turn deleted by turnId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> removeTurn(@RequestHeader String turnId) {
        try {
            if (turnId != null) {
                try {
                    turnService.deleteTurn(turnId);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The turn has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

}


