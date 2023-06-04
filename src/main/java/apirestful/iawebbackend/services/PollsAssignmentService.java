package apirestful.iawebbackend.services;
import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.PollsAssignment;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.model.Poll;
import apirestful.iawebbackend.repository.PollRepository;
import apirestful.iawebbackend.repository.PollsAssignmentRepository;
import apirestful.iawebbackend.repository.QuestionaryGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PollsAssignmentService {

    @Autowired
    PollsAssignmentRepository pollsAssignmentRepository;

    @Autowired
    QuestionaryGroupRepository questionaryGroupRepository;

    @Autowired
    PollRepository pollRepository;

    /**
     * @return list of all polls assignments
     */
    public List<PollsAssignment> getAllPA() {
        List<PollsAssignment>  PollsAssignment =  pollsAssignmentRepository.findAll();
        return  PollsAssignment;
    }

    /**
     *
     * @param pollsAssignment
     * @param qgId
     * @return the poll assignment recently created or updated
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public PollsAssignment createPollAssignment(PollsAssignment pollsAssignment, Long qgId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (pollsAssignment != null && !qgId.toString().isEmpty()) {
                try {
                    Optional<QuestionaryGroup> questionaryGroup = questionaryGroupRepository.findById(qgId);
                    if (questionaryGroup.isPresent()) {
                        pollsAssignment.setQuestionaryGroup(questionaryGroup.get());

                        if (pollsAssignment.getId() == null) {
                            //Defect Poll at the moment of create PollAssignment
                            Poll poll = new Poll();
                            poll.setActive(true);
                            poll.setCompleted(false);
                            poll.setOnLoad(false);
                            poll.setSigned(false);
                            poll.setCreate_Date(new Date());
                            poll.setPollsAssignment(pollsAssignment);
                            pollRepository.save(poll);
                        }

                        pollsAssignmentRepository.save(pollsAssignment);

                        return pollsAssignment;
                    }else {
                        throw new RecordNotFoundException("Questionnaire Group not found with id: " + qgId);
                    }

                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }

            } else {
                throw new RecordNotFoundException("Poll Assignment not sent or name is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

    /**
     *
     * @param idNavision
     * @return a list of pollsAssignments of one user
     */
    public List<PollsAssignment> getPollsAssignmentsByUser(String idNavision) {
        return pollsAssignmentRepository.pollsAssignmentsByUser(idNavision);
    }

    public PollsAssignment getPAbyID(String id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                PollsAssignment pollsAssignment = pollsAssignmentRepository.getPAbyID(id);
                if(pollsAssignment!=null){
                    return pollsAssignment;
                }else{
                    throw new RecordNotFoundException("The PollsAssignment with id: " + id + " dont exist");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }

}
