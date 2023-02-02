package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping("/assignRolToUser/{userId}/{rolId}")
    public boolean assignRolToUser(@PathVariable String userId, @PathVariable Long rolId) {
        return rolService.assignRolToUser(userId, rolId);
    }

    @DeleteMapping("/denyRolToUser/{userId}/{rolId}")
    public boolean denyRolToUser(@PathVariable String userId, @PathVariable Long rolId) {
        return rolService.denyRolToUser(userId, rolId);
    }

    @GetMapping("/getUsersOfOneRol/{rolId}")
    public List<User> getUsersOfOneRol(@PathVariable Long rolId) {
        return rolService.getUsersOfOneRol(rolId);
    }

    @GetMapping("UserIsAdmin/{userId}")
    public boolean isAdmin(@PathVariable String userId) {
        return rolService.isAdmin(userId);
    }

    @GetMapping("UserIsEvaluador/{userId}")
    public boolean isEvaluador(@PathVariable String userId) {
        return rolService.isEvaluador(userId);
    }

    @GetMapping("UserIsSocio/{userId}")
    public boolean isSocio(@PathVariable String userId) {
        return rolService.isSocio(userId);
    }

}
