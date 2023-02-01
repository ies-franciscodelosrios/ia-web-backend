package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.Turn;
import apirestful.iawebbackend.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/turn")
public class TurnController {
    @Autowired
    private TurnService turnService;

    @GetMapping(value = {"/getAllTurns"})
    public ResponseEntity<List<Turn>> getAllTurns() {
        try {
            return new ResponseEntity<List<Turn>>(turnService.getAllTurns(), new HttpHeaders(), HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La petición no se ha realizado correctamente");
        }
    }

    @GetMapping("/getTurn/{turnId}")
    public ResponseEntity<Turn> getTurnById(@PathVariable Long turnId) {
        try {
            return new ResponseEntity<Turn>(turnService.getTurnById(turnId), new HttpHeaders(), HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un turno con ese identificador");
        }
    }

    @GetMapping("/getUserTurns/{userId}")
    public ResponseEntity<Set<Turn>> getUserTurns(@PathVariable String userId) {
        try {
            return new ResponseEntity<Set<Turn>>(turnService.getUserTurns(userId), new HttpHeaders(), HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un usuario con ese identificador");
        }
    }

    @PostMapping("/save/assignUser/{userId}")
    public ResponseEntity<Turn> createTurn(@RequestBody Turn turn, @PathVariable String userId) {
        try {
            return new ResponseEntity<Turn>(turnService.createTurn(turn, userId), new HttpHeaders(), HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un usuario con ese identificador");
        }
    }

    @PutMapping("update/{turnId}")
    public ResponseEntity<Turn> updateTurn(@PathVariable Long turnId, @RequestBody Turn turn){
        try {
            return new ResponseEntity<Turn>(turnService.updateTurn(turn, turnId).getBody(), new HttpHeaders(), HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un turno con ese identificador");
        }
    }

    @DeleteMapping("/delete/{turnId}")
    public ResponseEntity removeTurn(@PathVariable Long turnId) {
        turnService.deleteTurn(turnId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
