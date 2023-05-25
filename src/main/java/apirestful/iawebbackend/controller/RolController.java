package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.Rol;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.security.UserDetailServiceImpl;
import apirestful.iawebbackend.services.RolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RestController
@Api(tags = "Rol")
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    @Autowired
    UserDetailServiceImpl userDetailService;

    /**
     * @param userId
     * @param rolId
     * @return Return true if the role could be assigned and false if not
     */
    @ApiOperation(value = "Assign one rol to user", notes = "Return true if the role could be assigned and false if not ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("assignRolToUser/{userId}/{rolId}")
    public ResponseEntity<Boolean> assignRolToUser(@PathVariable String userId, @PathVariable Long rolId) {
        try {
            if (!userId.isEmpty()) {
                try {
                    return new ResponseEntity<Boolean>(rolService.assignRolToUser(userId, rolId), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The userId or rolId are empty");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

    /**
     * @param userId
     * @param rolId
     * @return Return true if the role could be denied and false if not
     */
    @ApiOperation(value = "Denied one rol to user", notes = "Return true if the role could be denied and false if not ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("denyRolToUser/{userId}/{rolId}")
    public ResponseEntity<Boolean> denyRolToUser(@PathVariable String userId, @PathVariable Long rolId) {
        try {
            if (!userId.isEmpty()) {
                try {
                    return new ResponseEntity<Boolean>(rolService.denyRolToUser(userId, rolId), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The userId or rolId are empty");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

    /**
     * @param rolId
     * @return list of users who have this role
     */
    @ApiOperation(value = "List of users of one rol", notes = "Return a list of users who have this role ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("getUsersOfOneRol")
    public ResponseEntity<List<User>> getUsersOfOneRol(@RequestHeader Long rolId) {
        try {
            if (!rolId.toString().isEmpty()) {
                try {
                    return new ResponseEntity<List<User>>(rolService.getUsersOfOneRol(rolId), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The rolId are empty");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

    /**
     * @param userId
     * @return true if the user is admin and false if it is not
     */
    @ApiOperation(value = "Check if the user is admin", notes = "Return true if the user is admin and false if it is not ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("UserIsAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestHeader String userId) {
        try {
            if (!userId.isEmpty()) {
                try {
                    return new ResponseEntity<Boolean>(rolService.isAdmin(userId), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The userId are empty");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

    /**
     * @param userId
     * @return true if the user is evaluador and false if it is not
     */
    @ApiOperation(value = "Check if the user is evaluador", notes = "Return true if the user is evaluador and false if it is not ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("UserIsEvaluador")
    public ResponseEntity<Boolean> isEvaluador(@RequestHeader String userId) {
        try {
            if (!userId.isEmpty()) {
                try {
                    return new ResponseEntity<Boolean>(rolService.isEvaluador(userId), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The userId are empty");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

    /**
     * @param userId
     * @return true if the user is socio and false if it is not
     */
    @ApiOperation(value = "Check if the user is socio", notes = "Return true if the user is socio and false if it is not ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("UserIsSocio")
    public ResponseEntity<Boolean> isSocio(@RequestHeader String userId) {
        try {
            if (!userId.isEmpty()) {
                try {
                    return new ResponseEntity<Boolean>(rolService.isSocio(userId), new HttpHeaders(), HttpStatus.OK);
                } catch (ResponseStatusException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has failed by data");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The userId are empty");
            }
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The request has failed by permission",e);
        }
    }

    /**
     * @return Get all rols
     */

    @ApiOperation(value = "Get all rols", notes = "Returns a rol list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Rol>> getAllRols() {
        try {
            List<Rol> all = rolService.getAllRols();
            return new ResponseEntity<List<Rol>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getReason(), e);
        }
    }

}
