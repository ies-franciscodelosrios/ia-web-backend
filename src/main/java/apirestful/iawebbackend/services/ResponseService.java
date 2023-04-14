package apirestful.iawebbackend.services;
import apirestful.iawebbackend.model.Response;
import apirestful.iawebbackend.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {


    @Autowired
    ResponseRepository responseRepository;
    public List<Response> getOrderItemsByOrderId() {
        List<Response> order = responseRepository.findAll();
        return order;
    }
}
