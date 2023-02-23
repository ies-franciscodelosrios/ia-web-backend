package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.model.UserRelationsPK;
import apirestful.iawebbackend.model.UsersRelations;
import apirestful.iawebbackend.repository.UserRelationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRelationsService {
    @Autowired
    private UserRelationsRepository userRelationsRepository;


    /**
     * @return a list of all User-Relations
     * @throws RecordNotFoundException
     * @throws NullPointerException
     */
    public List<UsersRelations> getAllUserRelations() throws RecordNotFoundException,NullPointerException {
        try {
            List<UsersRelations> result = userRelationsRepository.findAll();
            if (!result.isEmpty()) {
                return result;
            } else {
                throw new RecordNotFoundException("Dont have User-Relations");
            }
        }catch (NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }


    /**
     * @param UserRelation
     * @return a User-Relation updated
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public UsersRelations createUserRelation(UsersRelations UserRelation) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (UserRelation != null) {
                try {
                    UserRelation = userRelationsRepository.save(UserRelation);
                    return UserRelation;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }

            } else {
                throw new RecordNotFoundException("Dont have User-Relation");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }

    }


    /**
     * @param pk
     * @return a User-Relation updated
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public UsersRelations UpdateUserRelation(UserRelationsPK pk) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (pk != null) {
                try {
                    UsersRelations ur = userRelationsRepository.getReferenceById(pk);
                    ur.setActive(false);
                    userRelationsRepository.save(ur);
                    return ur;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }

            }else{
                throw new RecordNotFoundException("Dont have User-Relation");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }


    }


    /**
     * @param pk
     * @return a User-Relation active by IdNavision
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public UsersRelations getUserByIdNavision(UserRelationsPK pk) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (pk != null) {
                try {
                    UsersRelations ur = userRelationsRepository.getReferenceById(pk);
                    return ur;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }else{
                throw new RecordNotFoundException("Dont have User-Relation");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }
    }

    /**
     * @param pk
     * @return a User-Relation active by User
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public List<String> getNameActiveRelationsByUser(String pk) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
       try {
           if (pk != null) {
               try {
                   List<String> ur = userRelationsRepository.getActivateRelationsName(pk);
                   return ur;
               } catch (IllegalArgumentException e) {
                   throw new IllegalArgumentException(e);
               }
           }else{
               throw new RecordNotFoundException("Dont have User-Relation");
           }
       }catch (NullPointerException e){
           throw new NullPointerException("Null value"+ e);
       }
    }
}
