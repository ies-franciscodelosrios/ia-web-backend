package apirestful.iawebbackend.services;
import apirestful.iawebbackend.exceptions.RecordNotFoundException;
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
    TextRelationRepository textRelationrepo;



    public List<Response> getAllResponses() {
        List<Response> order = responseRepository.findAll();
        return order;
    }

    /**
     * @param response
     * @return Create a Response
     * @throws RecordNotFoundException
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Response createResponse(Response response) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        try {
            Response idGET = responseRepository.getResponseByID(response.getId());
            if(idGET == null){
                try {
                    Response modelObject;
                    modelObject= responseRepository.save(response);
                    return modelObject;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(e);
                }
            }else{
                throw new RecordNotFoundException("The Response have already exist");
            }
        }catch (NullPointerException e){
            throw new NullPointerException("Null value"+ e);
        }
    }
}
