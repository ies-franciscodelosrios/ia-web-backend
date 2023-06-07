package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    /**
     * @return a list of users
     * @throws RecordNotFoundException
     */
    public List<User> getAllUsers() throws RecordNotFoundException, NullPointerException {
        try {
            List<User> result = userRepository.findAll();
            if (!result.isEmpty()) {
                return result;
            } else {
                throw new RecordNotFoundException("Dont have users");
            }
        }catch (NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * @param user
     * @return A user created with a data
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public User createUser(User user) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {

        try {
            User login = userRepository.getByIdNavision(user.getLogin());
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            if(login == null){
                try {
                    if(user.getProfile_Picture()==""){
                        user.setProfile_Picture("https://res.cloudinary.com/dgzlsuwnt/image/upload/v1676912069/profile_fcw78c.jpg");
                        user = userRepository.save(user);
                    }else{
                        user = userRepository.save(user);
                    }
                    return user;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }else{
                throw new RecordNotFoundException("The user have already exist");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }

    }


    /**
     *
     * @param user
     * @return a updated user with data
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public User updateUser(User user) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (user != null) {
                try {
                    user.setRols(user.getRols());
                    user.setEvents(user.getEvents());
                    user.setTurns(user.getTurns());
                    user = userRepository.save(user);
                    return user;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }

            } else {
                throw new RecordNotFoundException("The user dont exist");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }

    }


    /**
     * @return a user deleted by IdNavision
     * @param id_navision
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void deleteUserByIdNavision(String id_navision) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (id_navision != null && !id_navision.isEmpty()) {
            try {
                User user = userRepository.getByIdNavision(id_navision);
                if(user!=null){
                    userRepository.delete(user);
                }else{
                    throw new RecordNotFoundException("The user with IdNavision: " + id_navision + " dont exist");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @return get user by DNI
     * @param dni
     * @return
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public User getUserByDNI(String dni) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (dni != null && !dni.isEmpty()) {
            try {
                User user = userRepository.getByCodigo(dni);
                if(user!=null){
                    return user;
                }else{
                    throw new RecordNotFoundException("The user with dni: " + dni + " dont exist");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }

    /**
     *
     * @param idnavision
     * @return get user by IdNavision
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public User  getUserByIdNavision(String idnavision) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (idnavision != null) {
            try {
                User user = userRepository.getByIdNavision(idnavision);
                if(user!=null){
                    return user;
                }else{
                    throw new RecordNotFoundException("The user with IdNavision: " + idnavision + " dont exist");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }
    public ResponseEntity<User> setActiveUser(User user, String active){
        try{
            user.setActive(Boolean.parseBoolean(active));
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }catch (RecordNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The request was not processed correctly",ex);
        }
    }
}
