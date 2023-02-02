package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.Rol;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.RolRepository;
import apirestful.iawebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean assignRolToUser(String userId, Long rolId) {
        try {
            User user = userRepository.findById(userId).get();
            Rol rol = rolRepository.findById(rolId).get();

            if (user.getRols().contains(rol)) return false;

            for(Rol auxrol : user.getRols()) {
                if ((auxrol.getId() == 2 && rolId == 3) || (auxrol.getId() == 3 && rolId == 2)) {
                    return false;
                }
            }

            user.getRols().add(rol);
            userRepository.save(user);
            return true;
        }
        catch (Exception e) {
            throw new RecordNotFoundException("No se encuentra el usuario o rol con el código especificado");
        }
    }

    public boolean denyRolToUser(String userId, Long rolId) {
        User user = userRepository.findById(userId).get();
        Rol rol = rolRepository.findById(rolId).get();
        if (user.getRols().contains(rol)) {
            user.getRols().remove(rol);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean isAdmin(String userId) {
        User user = userRepository.findById(userId).get();
        Rol AdminRol = rolRepository.findById(1L).get();
        for(Rol rol : user.getRols()) {
            if (rol.equals(AdminRol)) return true;
        }
        return false;
    }

    public boolean isEvaluador(String userId) {
        User user = userRepository.findById(userId).get();
        Rol EvaluadorRol = rolRepository.findById(3L).get();
        for(Rol rol : user.getRols()) {
            if (rol.equals(EvaluadorRol)) return true;
        }
        return false;
    }

    public boolean isSocio(String userId) {
        User user = userRepository.findById(userId).get();
        Rol SocioRol = rolRepository.findById(2L).get();
        for(Rol rol : user.getRols()) {
            if (rol.equals(SocioRol)) return true;
        }
        return false;
    }

    public List<User> getUsersOfOneRol(Long rolId) {
        List<User> users = new ArrayList<>();
        List<String> usersId = rolRepository.userByRol(rolId);
        for(String userId : usersId) {
            users.add(userRepository.findById(userId).get());
        }
        return users;
    }

}
