package apirestful.iawebbackend.services;
import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.QuestionaryGroupRepository;

import apirestful.iawebbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionaryGroupService {

    @Autowired
    QuestionaryGroupRepository questionaryGroupRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * @return list of all questionnaire groups
     */
    public List<QuestionaryGroup> getAllQuestionnariesGroups() {
        List<QuestionaryGroup> allQuestionnaireGroups = questionaryGroupRepository.findAll();
        return allQuestionnaireGroups;
    }

    /**
     * @return list of all inactives questionnaire groups
     */
    public List<QuestionaryGroup> getAllInactiveQuestionaryGroup() {
        return questionaryGroupRepository.getAllInactiveQuestionaryGroup();
    }


    /**
     * @param questionaryGroup
     * @return Create a QuestionnaireGroup
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
                throw new RecordNotFoundException("The questionnaireGroup have already exist");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }
    }

    /**
     * @param idNavision
     * @return questionnaire of indicate user
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public List<QuestionaryGroup> getQuestionaryGroupsByUser(String idNavision) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try {
            User user = userRepository.getByIdNavision(idNavision);
            System.out.print(user);
            if(user != null){
                try {
                    QuestionaryGroup modelObject;
                    List<QuestionaryGroup> qgList= questionaryGroupRepository.questionaryGroupsByUser(idNavision);
                    return qgList;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }else{
                throw new RecordNotFoundException("The User with idNavision: " + idNavision + " haven't already exist");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }
    }

    /**
     * @param qgId
     * @return Get a QuestionnaireGroup by Id
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public QuestionaryGroup getQuestionaryGroupbyID(String qgId) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try {
            QuestionaryGroup questionnaireGroup = questionaryGroupRepository.getQuestionaryGroupbyID(Long.valueOf(qgId));
            if(questionnaireGroup != null){
                try {
                    return questionnaireGroup;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }else{
                throw new RecordNotFoundException("The QuestionnaireGroup with id: " + qgId + " has not found");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }
    }

    /**
     * @param qgId
     * @param questionaryGroup
     * @return a updated questionnaire
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public QuestionaryGroup updateQuestionnaire(String qgId, QuestionaryGroup questionaryGroup) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (questionaryGroup != null && !qgId.isEmpty()) {
                try {
                    Optional<QuestionaryGroup> OptionalQuestionaryGroup = questionaryGroupRepository.findById(Long.valueOf(qgId));

                    if(!OptionalQuestionaryGroup.isPresent()){
                        throw new RecordNotFoundException("Questionnaire not found with qgId: " + qgId);
                    }

                    questionaryGroup.setId(OptionalQuestionaryGroup.get().getId());

                    return questionaryGroupRepository.save(questionaryGroup);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }

            } else {
                throw new RecordNotFoundException("The questionnaire don't exist or qgId is empty");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value");
        }
    }

}
