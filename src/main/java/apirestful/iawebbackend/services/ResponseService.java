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
import java.util.Optional;

@Service
public class ResponseService {


    @Autowired
    ResponseRepository responseRepository;

    @Autowired
    TextRelationService textRelationService;

    PollsAssignmentService pollsAssignmentService;



    public Optional<Response> getAllResponses() {
        int id=1;
        Long idlong= (long) id;
        Optional<Response> responses = responseRepository.findById(idlong);
        return responses;
    }

    /**
     * @param response
     * @return Create a Response
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void createResponse(Response response, int pa_id) throws RecordNotFoundException, IllegalArgumentException {
        int integerValue=response.getInteger_Value();
        String textValue=response.getText_Value();
        try {
            try {
                        Optional<Response> checkID= responseRepository.findById(response.getId());
                        if (checkID.isPresent()){
                            responseRepository.updateReponse(response.getInteger_Value(),response.getText_Value(),response.getTextRelation().getRelationId().toString(),Integer.toString(pa_id),response.getId());
                        }else{
                            Response dataResponse= new Response();
                            dataResponse.setText_Value(textValue);
                            dataResponse.setInteger_Value(integerValue);
                            dataResponse.setTextRelation(response.getTextRelation());
                            responseRepository.createResponse(dataResponse.getInteger_Value(),dataResponse.getText_Value(),dataResponse.getTextRelation().getRelationId().toString(),Integer.toString(pa_id));
                        }





            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Null value: " + e);
        }
    }




}
