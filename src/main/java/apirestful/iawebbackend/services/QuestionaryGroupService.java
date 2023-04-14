package apirestful.iawebbackend.services;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.repository.QuestionaryGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionaryGroupService {

    @Autowired
    QuestionaryGroupRepository questionaryGroupRepository;

    public List<QuestionaryGroup> getOrderItemsByOrderId() {
        List<QuestionaryGroup> order = questionaryGroupRepository.findAll();
        return order;
    }

}
