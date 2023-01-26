package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> { }
