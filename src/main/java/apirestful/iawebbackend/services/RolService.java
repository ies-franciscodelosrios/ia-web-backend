package apirestful.iawebbackend.services;

import apirestful.iawebbackend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;
}
