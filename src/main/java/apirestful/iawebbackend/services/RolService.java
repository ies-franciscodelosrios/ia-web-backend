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
import java.util.Optional;


@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param userId
     * @param rolId
     * @return true if the role could be assigned and false if not
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean assignRolToUser(String userId, Long rolId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (!userId.isEmpty() && rolId!=null) {
                try {
                    Optional<User> user = userRepository.findById(userId);
                    Optional<Rol> rol = rolRepository.findById(rolId);

                    if (!user.isPresent()) throw new RecordNotFoundException("User with userId: " + userId + " not found");
                    if (!rol.isPresent()) throw new RecordNotFoundException("Rol with rolId: " + rolId + " not found");

                    if (user.get().getRols().contains(rol.get())) return false;

                    for(Rol auxrol : user.get().getRols()) {
                        if ((auxrol.getId() == 2 && rolId == 3) || (auxrol.getId() == 3 && rolId == 2)) {
                            return false;
                        }
                    }
                    user.get().getRols().add(rol.get());
                    userRepository.save(user.get());
                    return true;

                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                throw new RecordNotFoundException("userId or rolID are empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @param userId
     * @param rolId
     * @return true if the role could be denied and false if not
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean denyRolToUser(String userId, Long rolId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (!userId.isEmpty() && rolId!=null) {
                try {
                    Optional<User> user = userRepository.findById(userId);
                    Optional<Rol> rol = rolRepository.findById(rolId);

                    if (!user.isPresent()) throw new RecordNotFoundException("User with userId: " + userId + " not found");
                    if (!rol.isPresent()) throw new RecordNotFoundException("Rol with rolId: " + rolId + " not found");

                    if (user.get().getRols().contains(rol.get())) {
                        user.get().getRols().remove(rol.get());
                        userRepository.save(user.get());
                        return true;
                    }
                    return false;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                throw new RecordNotFoundException("userId or rolID are empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @param userId
     * @return true if the user is admin and false if it is not
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean isAdmin(String userId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (!userId.isEmpty()) {
                try {
                    User user = userRepository.getByIdNavision(userId);
                    if (user != null) {
                        Rol AdminRol = rolRepository.findById(1L).get();
                        for(Rol rol : user.getRols()) {
                            if (rol.equals(AdminRol)) return true;
                        }
                        return false;
                    } else {
                        throw new RecordNotFoundException("User with userId: " + userId + " is not Admin");
                    }

                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                throw new RecordNotFoundException("userId is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @param userId
     * @return true if the user is evaluador and false if it is not
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean isEvaluador(String userId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (!userId.isEmpty()) {
                try {
                    Optional<User> user = userRepository.findById(userId);
                    if (user.isPresent()) {
                        Rol EvaluadorRol = rolRepository.findById(3L).get();
                        for (Rol rol : user.get().getRols()) {
                            if (rol.equals(EvaluadorRol)) return true;
                        }
                        return false;
                    } else {
                        throw new RecordNotFoundException("User with userId: " + userId + " is not Evaluator ");
                    }
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                throw new RecordNotFoundException("userId is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @param userId
     * @return true if the user is socio and false if it is not
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public boolean isSocio(String userId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (!userId.isEmpty()) {
                try {
                    Optional<User> user = userRepository.findById(userId);
                    if (user.isPresent()) {
                        Rol SocioRol = rolRepository.findById(2L).get();
                        for (Rol rol : user.get().getRols()) {
                            if (rol.equals(SocioRol)) return true;
                        }
                        return false;
                    }
                    else {
                        throw new RecordNotFoundException("User with userId: " + userId + " is not Socio");
                    }
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                throw new RecordNotFoundException("userId is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @param rolId
     * @return list of users who have this role
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public List<User> getUsersOfOneRol(Long rolId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (!rolId.toString().isEmpty()) {
                try {
                    List<User> users = new ArrayList<>();
                    List<String> usersId = rolRepository.userByRol(rolId);
                    for(String userId : usersId) {
                        users.add(userRepository.findById(userId).get());
                    }
                    return users;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                throw new RecordNotFoundException("rolId is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @return a list of rols
     * @throws RecordNotFoundException
     */
    public List<Rol> getAllRols() throws RecordNotFoundException, NullPointerException {
        try {
            List<Rol> result = rolRepository.findAll();
            if (!result.isEmpty()) {
                return result;
            } else {
                throw new RecordNotFoundException("Dont have rols");
            }
        }catch (NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }

}
