package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.model.UserRelationsPK;
import apirestful.iawebbackend.model.UsersRelations;
import apirestful.iawebbackend.services.UserRelationsService;
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


    @GetMapping
    public ResponseEntity<List<UsersRelations>> getAllUsersRelations() {
        try {
            List<UsersRelations> all = userRelationsService.getAllUserRelations();
            return new ResponseEntity<List<UsersRelations>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<UsersRelations> all = userRelationsService.getAllUserRelations();
            return new ResponseEntity<List<UsersRelations>>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }


    @PutMapping("/{id1}/{id2}")
    public ResponseEntity<UsersRelations> updateUserRelation(@PathVariable String id1, @PathVariable String id2) throws ResponseStatusException {
        if (id1 != null) {
            try {
                UserRelationsPK id= new UserRelationsPK();
                id.setIdNavision(id1);
                id.setIdNavision2(id2);
                UsersRelations ur= userRelationsService.UpdateUserRelation(id);
                return new ResponseEntity<UsersRelations>(ur, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El ni�o no ha sido eliminado correctamente",
                        e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }

    }


    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<UsersRelations> getUserRelationbyIDNavision(@PathVariable String id1, @PathVariable String id2) throws ResponseStatusException {
        if (id1 != null) {
            try {
                UserRelationsPK id= new UserRelationsPK();
                id.setIdNavision(id1);
                id.setIdNavision2(id2);
                UsersRelations ur= userRelationsService.getUserByIdNavision(id);
                return new ResponseEntity<UsersRelations>(ur, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El ni�o no ha sido eliminado correctamente",
                        e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }

    }


    @PostMapping
    public ResponseEntity<UsersRelations> createUserRelation(@RequestBody UsersRelations n) throws ResponseStatusException {
        if (n != null) {
            try {
                UsersRelations kid = userRelationsService.createUserRelation(n);
                return new ResponseEntity<UsersRelations>(kid, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nino no ha sido guardado correctamente", e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }
    }
}
