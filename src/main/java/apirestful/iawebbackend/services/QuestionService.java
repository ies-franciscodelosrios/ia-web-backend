package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;



    /**
     * @param question
     * @return Create a QuestionaryGroup
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Question createQuestion(Question question) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try {
            Question idGET = questionRepository.getQuestionbyID(question.getId());
            if(idGET == null){
                try {
                    Question modelObject;
                    modelObject= questionRepository.save(question);
                    return modelObject;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }else{
                throw new RecordNotFoundException("The Question have already exist");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }
    }


    /**
     * @param question
     * @return Create a Question
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Question updateQuestion(Question question) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try{
            if (question != null && question.getId()!=null) {
                try {
                    Optional<Question> OptionalTurn = questionRepository.findById(question.getId());

                    if(!OptionalTurn.isPresent()){
                        throw new RecordNotFoundException("Question not found with ID: " + question.getId());
                    }
                    question.setText(question.getText());
                    question.setType(question.getType());
                    questionRepository.save(question);
                    return question;
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
     * @return a user deleted by IdNavision
     * @param id
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void deleteQuestionByID(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (id != null && id!=null) {
            try {
                Question question = questionRepository.getQuestionbyID(id);
                if(question!=null){
                    questionRepository.delete(question);
                }else{
                    throw new RecordNotFoundException("The question with id: " + id + " dont exist");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }


    /**
     * @return a user deleted by IdNavision
     * @param id
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Question getQuestionByID(String id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                Question question = questionRepository.getQuestionbyID(Long.valueOf(id));
                if(question!=null){
                    return question;
                }else{
                    throw new RecordNotFoundException("The question with id: " + id + " dont exist");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Null value");
        }
    }



}
