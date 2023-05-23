package apirestful.iawebbackend.services;
import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.*;
import apirestful.iawebbackend.repository.QuestionRepository;
import apirestful.iawebbackend.repository.QuestionaryGroupRepository;
import apirestful.iawebbackend.repository.TextRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TextRelationService {


    private final TextRelationRepository textRelationRepository;
    public final QuestionaryGroupRepository questionaryGroupRepository;
    private final QuestionRepository questionRepository;

    private final QuestionService questionService;
    public QuestionaryGroupService questionaryGroupService;

    @Autowired
    public TextRelationService(final TextRelationRepository textRelationRepository,
                               final QuestionaryGroupRepository questionaryGroupRepository,
                               final QuestionRepository questionRepository,
                               QuestionService questionService, final QuestionaryGroupService questionaryGroupService) {
        this.textRelationRepository = textRelationRepository;
        this.questionaryGroupRepository = questionaryGroupRepository;
        this.questionRepository = questionRepository;
        this.questionService = questionService;
        this.questionaryGroupService = questionaryGroupService;
    }


    /*
        Get all Text-Relation
     */
    public List<TextRelation> getAlltr() {
        List<TextRelation> textRelations = textRelationRepository.findAll();
        if(textRelations!=null){
            return textRelations;
        }else{
            throw new RecordNotFoundException("Not Found Records");
        }
    }


    /**
     * @param textRelation
     * @return Create a Response
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public TextRelation createTR(TextRelation textRelation, String id_questionary_group, String id_question) throws RecordNotFoundException, NullPointerException {
        try {
            TextRelation idGET = textRelationRepository.getTRbyID(textRelation.getRelationId());
            QuestionaryGroup questionaryGroup= questionaryGroupService.getQuestionaryGroupbyID(id_questionary_group);
            Question question = questionService.getQuestionByID(id_question);

            if(idGET==null){
                    TextRelation modelObject= textRelation;
                    modelObject.setQuestionaryGroup(questionaryGroup);
                    modelObject.setQuestion(question);
                    modelObject= textRelationRepository.save(modelObject);
                    return modelObject;
            } else {
                throw new RecordNotFoundException("The Response have already exist");
            }
            }catch (NullPointerException e) {
            throw new NullPointerException("Null value" + e);
        }
    }

    /**
     * @param questionary_group_id
     * @return Return a all questions of a one questionary Group
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public List<Question> getAllQuestionsBySurvey(Long questionary_group_id) throws RecordNotFoundException, NullPointerException {
        if(questionary_group_id!=null){
            List<Question> modelObject;
            modelObject= questionRepository.getAllQuestionsBySurvey(questionary_group_id);
            if(modelObject!=null){
                return modelObject;
            }else{
                throw new RecordNotFoundException("Not Found Questions for this Survey");
            }

        }else{
            throw new NullPointerException("Null value");
        }
    }


    /**
     * @param questionary_group_id
     * @return Return a all questions of a one questionary Group
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public List<Map<Question,Response>> getAllResponsesByUserSurvey(Long questionary_group_id,String id_navision) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if(questionary_group_id!=null && !id_navision.isEmpty()){
            List<Map<Question,Response>> modelObject;
            modelObject= textRelationRepository.getResponsebyQGbyUser(questionary_group_id,id_navision);
            if(modelObject!=null){
                System.out.println(modelObject);
                return modelObject;
            }else {
                throw new RecordNotFoundException("No responses found in this Survey");
            }
        }else {
            throw new NullPointerException("Null value");
        }
    }

    /**
     * @param textRelation
     * @return Create a Response
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public TextRelation getTRbyID( String id_tr) throws RecordNotFoundException, NullPointerException {
        try {
            TextRelation idGET = textRelationRepository.getTRbyID(Long.valueOf(id_tr));
            if(idGET!=null){
                TextRelation modelObject= idGET;
                return modelObject;
            } else {
                throw new RecordNotFoundException("The Response have not exist");
            }
        }catch (NullPointerException e) {
            throw new NullPointerException("Null value" + e);
        }
    }
}
