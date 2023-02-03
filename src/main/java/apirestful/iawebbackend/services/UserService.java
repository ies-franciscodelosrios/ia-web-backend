package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    /**
     * @return Una lista de todos los usuarios de la BBDD
     * @throws RecordNotFoundException
     */
    public List<User> getAllUsers() throws RecordNotFoundException {

        List<User> result = userRepository.findAll();
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new RecordNotFoundException("No se encuentran valores");
        }

    }

    /**
     * @param user
     * @return Usuario creado con unos determinados parámetros establecidos por el usuario
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public User createUser(User user) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {

        User login = userRepository.getByIdNavision(user.getLogin());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        if(login == null){
            try {
                if(user.getProfile_Picture()==""){
                    user.setProfile_Picture("https://res.cloudinary.com/dmcgwm5nm/image/upload/v1675328193/default_profile_ugadft.png");
                    user = userRepository.save(user);
                }else{

                    user = userRepository.save(user);

                }
                return user;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }

        }else{
            throw new RecordNotFoundException("El nombre ya existe");
        }
    }


    /**
     *
     * @param user
     * @return un usuario que ya existe actualizado con nuevos valores
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public User updateUser(User user) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {

        if (user != null) {
            try {
                user.setRols(user.getRols());
                user.setEvents(user.getEvents());
                user.setTurns(user.getTurns());
                user = userRepository.save(user);
                return user;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }

        } else {
            throw new NullPointerException("Valor nulo");
        }
    }


    /**
     * @return borra a un usuario en concreto con el id pasado por parametro (DNI)
     * @param codigo
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void deleteUserByDNI(String codigo) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (codigo != null) {
            try {
                Optional<User> kid = userRepository.findById(codigo);
                if (kid.isPresent()) {
                    userRepository.deleteById(codigo);

                }

            } catch (Exception e) {
                throw new IllegalArgumentException("Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }
        } else {
            throw new NullPointerException();
        }
    }


    /**
     * @return borra a un usuario en concreto con el id pasado por parametro (IDNAVISION)
     * @param id_navision
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void deleteUserByIdNavision(String id_navision) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        System.out.print(id_navision);
        if (id_navision != null) {
            try {
                User kid = userRepository.getByIdNavision(id_navision);
                if(kid!=null){
                    userRepository.delete(kid);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * @return un USUARIO en concreto de la BBDD con el id (CÓDIGO) pasado por parametro
     * @param codigo
     * @return
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public User getUserById(String codigo) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (codigo != null) {
            try {
                User user = userRepository.getByCodigo(codigo);
                if (user!=null) {
                     return user;
                } else {
                    throw new RecordNotFoundException("No se han encontrado valores para el id: ", codigo);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }
        } else {
            throw new NullPointerException("Valor nulo");
        }
    }

    /**
     *
     * @param idnavision
     * @return un usuario en concreto de la BBDD con el IDnavision concreto pasado por parametro
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public User  getUserByIdNavision(String idnavision) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (idnavision != null) {
            try {
                User user = userRepository.getByIdNavision(idnavision);
                return user;
            } catch (Exception e) {
                throw new IllegalArgumentException("Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }
        } else {
            throw new NullPointerException();
        }
    }
}
