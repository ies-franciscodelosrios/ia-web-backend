package apirestful.iawebbackend.services;
import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.PollsAssignment;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.Response;
import apirestful.iawebbackend.model.TextRelation;
import apirestful.iawebbackend.repository.ResponseRepository;
import apirestful.iawebbackend.repository.TextRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {


    @Autowired
    ResponseRepository responseRepository;

    @Autowired
    TextRelationService textRelationService;

    PollsAssignmentService pollsAssignmentService;



    public List<Response> getAllResponses() {
        List<Response> responses = responseRepository.findAll();
        return responses;
    }

    /**
     * @param response
     * @return Create a Response
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Response createResponse(Response response,String relation_Id, String pa_id) throws RecordNotFoundException, IllegalArgumentException {
        int integerValue=response.getInteger_Value();
        String textValue=response.getText_Value();
        try {
            try {
                response.setInteger_Value(integerValue);
                response.setText_Value(textValue);

                TextRelation textRelation = new TextRelation();
                textRelation.setRelationId(Long.valueOf(relation_Id));
                response.setTextRelation(textRelation);

                PollsAssignment pollsAssignment = new PollsAssignment();
                pollsAssignment.setId(Long.valueOf(pa_id));
                response.setPollsAssignment(pollsAssignment);

                return responseRepository.save(response);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Null value: " + e);
        }
    }




}
