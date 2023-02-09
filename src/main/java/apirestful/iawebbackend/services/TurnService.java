package apirestful.iawebbackend.services;

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

    public List<Turn> getAllTurns() {
        return turnRepository.findAll();
    }

    public Turn getTurnById(Long turnId) {
        Optional<Turn> OptionalTurn = turnRepository.findById(turnId);

        if(!OptionalTurn.isPresent()){
            return null;
        }

        return turnRepository.findById(turnId).get();
    }

    public Set<Turn> getUserTurns(String IdNavision) {
        User user = userRepository.getByIdNavision(IdNavision);
        return user.getTurns();
    }

    public Turn createTurn(Turn turn, String IdNavision) {
        User user = userRepository.getByIdNavision(IdNavision);
        turn.setUser(user);
        return turnRepository.save(turn);
    }

    public ResponseEntity<Turn> updateTurn(Turn turn, Long turnId) {
        Optional<Turn> OptionalTurn = turnRepository.findById(turnId);

        if(!OptionalTurn.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        turn.setCodigo(OptionalTurn.get().getCodigo());
        turn.setUser(OptionalTurn.get().getUser());
        turnRepository.save(turn);
        return ResponseEntity.ok().build();
    }

    public void deleteTurn(Long turnId) {
        turnRepository.deleteById(turnId);
    }

}
