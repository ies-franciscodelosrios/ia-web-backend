package apirestful.iawebbackend.services;
import apirestful.iawebbackend.model.PollsAssignment;
import atmira.demo.repository.PollsAssignmentRepository;
import atmira.demo.repository.QuestionaryGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollsAssignmentService {

    @Autowired
    PollsAssignmentRepository pollsAssignmentRepository;


}
