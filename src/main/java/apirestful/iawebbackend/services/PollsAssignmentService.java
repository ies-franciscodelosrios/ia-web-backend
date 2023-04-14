package apirestful.iawebbackend.services;
import apirestful.iawebbackend.model.PollsAssignment;
import apirestful.iawebbackend.repository.PollsAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollsAssignmentService {

    @Autowired
    PollsAssignmentRepository pollsAssignmentRepository;

    public List<PollsAssignment> getAllPA() {
        List<PollsAssignment>  PollsAssignment =  pollsAssignmentRepository.findAll();
        return  PollsAssignment;
    }

}
