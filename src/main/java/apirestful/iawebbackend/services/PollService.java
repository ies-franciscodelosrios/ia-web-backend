package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.Poll;
import apirestful.iawebbackend.model.PollsAssignment;
import apirestful.iawebbackend.repository.PollRepository;
import apirestful.iawebbackend.repository.PollsAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollsAssignmentRepository pollsAssignmentRepository;


    /**
     * @param paId
     * @param updatedPoll
     * @return a updated poll
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Poll updatePoll(String paId, Poll updatedPoll) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try {
            if (updatedPoll != null && !paId.isEmpty()) {
                Optional<PollsAssignment> optionalPollAssignment = pollsAssignmentRepository.findById(Long.valueOf(paId));

                if (!optionalPollAssignment.isPresent()) {
                    throw new RecordNotFoundException("Poll not found with paId: " + paId);
                }

                Poll existingPoll = optionalPollAssignment.get().getPoll();
                existingPoll.setActive(updatedPoll.isActive());
                existingPoll.setCompleted(updatedPoll.isCompleted());
                existingPoll.setSigned(updatedPoll.isSigned());
                existingPoll.setOnLoad(updatedPoll.isOnLoad());

                return pollRepository.save(existingPoll);
            } else {
                throw new RecordNotFoundException("The poll doesn't exist or paId is empty");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Null value");
        }
    }

}
