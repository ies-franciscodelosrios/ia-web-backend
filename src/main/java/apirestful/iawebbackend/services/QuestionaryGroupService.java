package apirestful.iawebbackend.services;
import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.QuestionaryGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionaryGroupService {

    @Autowired
    QuestionaryGroupRepository questionaryGroupRepository;

    public List<QuestionaryGroup> getAllQuestionaryGroup() {
        List<QuestionaryGroup> questionaryGroups = questionaryGroupRepository.findAll();
        return questionaryGroups;
    }

    public QuestionaryGroup getQuestionaryGroupsById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException{
        if (id != null) {
            try {
                QuestionaryGroup questionaryGroup = questionaryGroupRepository.getQuestionaryGroupbyID(id);
                if(questionaryGroup!=null){
                    return questionaryGroup;
                }else{
                    throw new RecordNotFoundException("The questionaryGroup with id: " + id + " dont exist");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }


    /**
     * @param questionaryGroup
     * @return Create a QuestionaryGroup
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public QuestionaryGroup createQuestionaryGroup(QuestionaryGroup questionaryGroup) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try {
            QuestionaryGroup idGET = questionaryGroupRepository.getQuestionaryGroupbyID(questionaryGroup.getId());
            if(idGET == null){
                try {
                    QuestionaryGroup modelObject;
                    modelObject= questionaryGroupRepository.save(questionaryGroup);
                    return modelObject;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }else{
                throw new RecordNotFoundException("The questionaryGroup have already exist");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }
    }

    /**
     * @param questionaryGroup
     * @return Create a QuestionaryGroup
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public QuestionaryGroup getQuestionaryGroupbyID(QuestionaryGroup questionaryGroup) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try {
            QuestionaryGroup idGET = questionaryGroupRepository.getQuestionaryGroupbyID(questionaryGroup.getId());
            if(idGET == null){
                try {
                    QuestionaryGroup modelObject;
                    modelObject= questionaryGroupRepository.save(questionaryGroup);
                    return modelObject;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }else{
                throw new RecordNotFoundException("The questionaryGroup have already exist");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }
    }

}
