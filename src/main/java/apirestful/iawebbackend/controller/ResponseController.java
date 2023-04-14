package apirestful.iawebbackend.controller;
import apirestful.iawebbackend.model.Response;
import apirestful.iawebbackend.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/response")
public class ResponseController {

    @Autowired
    private ResponseService responseService;
    @GetMapping("/all")
    public ResponseEntity<List<Response>> getQG() throws ResponseStatusException {
        try {
            List<Response> all = responseService.getOrderItemsByOrderId();
            System.out.println(all);
            return new ResponseEntity<List<Response>>(all, new HttpHeaders(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            List<Response> all = responseService.getOrderItemsByOrderId();
            return new ResponseEntity<>(all, new HttpHeaders(), HttpStatus.OK);
        }
    }
}
