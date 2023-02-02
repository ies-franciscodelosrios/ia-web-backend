package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * @return todos los usuarios de la base de datos
     */
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> all = userService.getAllUsers();
            return new ResponseEntity<List<User>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<User> all = userService.getAllUsers();
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }


    /**
     * @param codigo
     * @return un usuario en concreto por su DNI
     * @throws ResponseStatusException
     */
    @GetMapping("/search/dni/{codigo}")
    public ResponseEntity<User> getUserById(@PathVariable("codigo") String codigo) throws ResponseStatusException {

        if (codigo != null) {
            try {
                User user = userService.getUserById(codigo);
                return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con id: " + codigo + "no se ha encontrado",e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }

    }

    /**
     * @param idnavision
     * @return un usuario en concreto por su IDNAVISION
     * @throws ResponseStatusException
     */
    @GetMapping("/search/id/{idnavision}")
    public ResponseEntity<User> getUserByIdNavision(@PathVariable("idnavision") String idnavision) throws ResponseStatusException {

        if (idnavision != null) {
            try {
                User user = userService.getUserByIdNavision(idnavision);
                return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con id: " + idnavision + "no se ha encontrado",e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }

    }


    /**
     * @param user
     * @return crear un usuario
     * @throws ResponseStatusException
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws ResponseStatusException {
        if (user != null) {
            try {
                User createuser = userService.createUser(user);
                return new ResponseEntity<User>(createuser, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no ha sido guardado correctamente", e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }
    }


    /**
     * @param user
     * @return un usuario actualizado ya existente con valores cambiados
     * @throws ResponseStatusException
     */
    @PutMapping
    public ResponseEntity<User> UpdateUser(@RequestBody User user) throws ResponseStatusException {
        if (user != null ) {
            try {
                User new_user = userService.updateUser(user);
                return new ResponseEntity<User>(new_user, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no ha sido actualizado correctamente",
                        e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }

    }

    /**
     * @param codigo
     * @return un usuario eliminado por su IDnavision
     * @throws ResponseStatusException
     */
    @DeleteMapping("/delete/id/{codigo}")
    public HttpStatus deleteUserbyIDnavision(@PathVariable("codigo") String codigo) throws ResponseStatusException {
        System.out.print(codigo);
        if (codigo != null) {
            try {
                userService.deleteUserByIdNavision(codigo);

                return HttpStatus.OK;
            } catch (ResponseStatusException e) {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El ni�o no ha sido eliminado correctamente",
                        e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }

    }

    /**
     * @param codigo
     * @return un usuario eliminado por su id
     * @throws ResponseStatusException
     */
    @DeleteMapping("/delete/dni/{codigo}")
    public HttpStatus deleteUserbyDNI(@PathVariable("codigo") String codigo) throws ResponseStatusException {
        System.out.print(codigo);
        if (codigo != null) {
            try {
                userService.deleteUserByDNI(codigo);

                return HttpStatus.OK;
            } catch (ResponseStatusException e) {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El ni�o no ha sido eliminado correctamente",
                        e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
        }

    }



}
