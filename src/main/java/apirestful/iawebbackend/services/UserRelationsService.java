package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.model.UserRelationsPK;
import apirestful.iawebbackend.model.UsersRelations;
import apirestful.iawebbackend.repository.UserRelationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserRelationsService {
    @Autowired
    private UserRelationsRepository userRelationsRepository;


    /**
     * @return Una lista de todos las relaciones con usuario de la BBDD
     * @throws RecordNotFoundException
     */
    public List<UsersRelations> getAllUserRelations() throws RecordNotFoundException {
        List<UsersRelations> result = userRelationsRepository.findAll();
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new RecordNotFoundException("No hay valores");
        }
    }


    /**
     * @param UserRelation
     * @return Crea una relacion entre dos usuarios con los parametros pasados en la BBDD
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public UsersRelations createUserRelation(UsersRelations UserRelation) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (UserRelation != null) {
            try {
                UserRelation = userRelationsRepository.save(UserRelation);
                return UserRelation;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }

        } else {
            throw new NullPointerException("Valor nulo");
        }
    }


    /**
     * @param pk
     * @return Desactiva la relacion de un usuario con otro dejando el campo active a false
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public UsersRelations UpdateUserRelation(UserRelationsPK pk) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (pk != null) {
            try {
               UsersRelations ur = userRelationsRepository.getReferenceById(pk);
               ur.setActive(false);
               userRelationsRepository.save(ur);
               return ur;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }

        } else {
            throw new NullPointerException("Valor nulo");
        }

    }


    /**
     * @param pk
     * @return Obtenemos una relacion entre dos usuarios en concretos, en funcion del idNavision de cada uno
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public UsersRelations getUserByIdNavision(UserRelationsPK pk) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (pk != null) {
            try {
                UsersRelations ur = userRelationsRepository.getReferenceById(pk);
                return ur;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
            }

        } else {
            throw new NullPointerException("Valor nulo");
        }

    }

}
