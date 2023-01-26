package apirestful.iawebbackend.services;

import apirestful.iawebbackend.repository.UserRelationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRelationsService {
    @Autowired
    private UserRelationsRepository userRelationsRepository;
}
