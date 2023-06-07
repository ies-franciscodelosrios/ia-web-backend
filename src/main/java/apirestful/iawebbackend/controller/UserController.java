package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "User")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;

    /**
     * @return Get all users
     */

    @ApiOperation(value = "Get all users", notes = "Returns a user list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> all = userService.getAllUsers();
            return new ResponseEntity<List<User>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getReason(), e);
        }
    }


    /**
     * @param codigo
     * @return Get a user by your DNI
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get a user by your DNI", notes = "Return a user with a DNI")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/search/dni")
    public ResponseEntity<User> getUserById(@RequestHeader String codigo) throws ResponseStatusException {
           try{
            if (codigo!=null && !codigo.isEmpty()){
                User user = userService.getUserByDNI(codigo);
                if (user!=null) {
                    return new ResponseEntity<>(user, HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user has not found");
                }
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
            }
        }catch (ResponseStatusException e) {
               throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission", e);
           }
    }

    /**
     * @param idnavision
     * @return Get a user by your IDNavision
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Get a user by your IDNavision", notes = "Return a user with a IDNavision")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/search/id")
    public ResponseEntity<User> getUserByIdNavision(@RequestHeader String idnavision) throws ResponseStatusException {
        try{
            if (idnavision!=null && !idnavision.isEmpty()){
                User user = userService.getUserByIdNavision(idnavision);
                if (user!=null) {
                    return new ResponseEntity <> (user, HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user has not found");
                }
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }


    /**
     * @param user
     * @return Create a user with a data
     */
    @ApiOperation(value = "Create a user with a data", notes = "Return a user with a data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if (user != null) {
                try {
                    User createuser = userService.createUser(user);
                    return new ResponseEntity<User>(createuser, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }

    }


    /**
     * @param user
     * @return un usuario actualizado ya existente con valores cambiados
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Update a user with a data", notes = "Return a user updated with a data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping
    public ResponseEntity<User> UpdateUser(@RequestBody User user) throws ResponseStatusException {
        try {
            if (user != null ) {
                try {
                    User new_user = userService.updateUser(user);
                    return new ResponseEntity<User>(new_user, new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }


    }

    /**
     * @param codigo
     * @return Delete a user by IDnavision
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Delete a user by IDnavision", notes = "Return a user deleted by IDnavision")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @DeleteMapping()
    public HttpStatus deleteUserbyIDnavision(@RequestHeader String codigo) throws ResponseStatusException {
        try {
            if (codigo != null) {
                try {
                    userService.deleteUserByIdNavision(codigo);
                    return HttpStatus.OK;
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user has not found");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }
    /**
     * @param idnavision
     * @param active
     * @return A user with attribute active modified
     * @throws ResponseStatusException
     */
    @ApiOperation(value = "Active a user by IDnavision", notes = "Return a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping ("/active")
    public ResponseEntity<?> ActiveUser(@RequestHeader String idnavision, @RequestHeader String active) {
        try{
            if (idnavision!=null && !idnavision.isEmpty()){
                User user = userService.getUserByIdNavision(idnavision);
                if (user!=null && !active.isEmpty()) {
                    userService.setActiveUser(user,active);
                    return new ResponseEntity <> (user, HttpStatus.OK);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user has not found");
                }
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }
}
