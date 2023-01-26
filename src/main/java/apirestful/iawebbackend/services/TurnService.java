package apirestful.iawebbackend.services;

import apirestful.iawebbackend.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnService {
    @Autowired
    private TurnRepository turnRepository;
}
