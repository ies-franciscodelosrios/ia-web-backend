package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.Turn;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.TurnRepository;
import apirestful.iawebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnService {
    @Autowired
    private TurnRepository turnRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * @return a list of turns
     * @throws RecordNotFoundException
     */
    public List<Turn> getAllTurns() throws RecordNotFoundException, NullPointerException {
        try {
            List<Turn> turns = turnRepository.findAll();
            if (!turns.isEmpty()) {
                return turns;
            } else {
                throw new RecordNotFoundException("Dont have turns");
            }
        }catch (NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * @return get turn by turnId
     * @param turnId
     * @return
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Turn getTurnById(String turnId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (turnId != null && !turnId.isEmpty()) {
            try {
                Optional<Turn> OptionalTurn = turnRepository.findById(turnId);
                if(OptionalTurn.isPresent()){
                    return turnRepository.findById(turnId).get();
                }else{
                    throw new RecordNotFoundException("The turn with id: " + turnId + " dont exist");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @param IdNavision
     * @return turns of current user
     */
    public Set<Turn> getUserTurns(String IdNavision) {
        try{
            if (!IdNavision.isEmpty()) {
                try {
                    User user = userRepository.getByIdNavision(IdNavision);
                    return user.getTurns();
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }

            } else {
                throw new RecordNotFoundException("IdNavision is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @param turn
     * @param IdNavision
     * @return create a turn and assign it to current user
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Turn createTurn(Turn turn, String IdNavision) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {

        try{
            if (turn != null && !IdNavision.isEmpty()) {
                try {
                    User user = userRepository.getByIdNavision(IdNavision);
                    if (user!=null) {
                        turn.setUser(user);
                        return turnRepository.save(turn);
                    }else {
                        throw new RecordNotFoundException("User not found with IdNavision: " + IdNavision);
                    }

                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }

            } else {
                throw new RecordNotFoundException("Turn not sent or IdNavision is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     *
     * @param turn
     * @param turnId
     * @return updated turn
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public ResponseEntity<Turn> updateTurn(Turn turn, String turnId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (turn != null && !turnId.isEmpty()) {
                try {
                    Optional<Turn> OptionalTurn = turnRepository.findById(turnId);

                    if(!OptionalTurn.isPresent()){
                        throw new RecordNotFoundException("Turn not found with turnId: " + turnId);
                    }

                    turn.setCodigo(OptionalTurn.get().getCodigo());
                    turn.setUser(OptionalTurn.get().getUser());
                    turnRepository.save(turn);
                    return ResponseEntity.ok().build();
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }

            } else {
                throw new RecordNotFoundException("The turn dont exist or turnId is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @return a turn deleted by turnId
     * @param turnId
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void deleteTurn(String turnId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException{

        if (turnId != null && !turnId.isEmpty()) {
            try {
                Optional<Turn> turn = turnRepository.findById(turnId);
                if(turn.isPresent()){
                    turnRepository.delete(turn.get());
                }else{
                    throw new RecordNotFoundException("The turn with turnId: " + turnId + " dont exist");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }

}
