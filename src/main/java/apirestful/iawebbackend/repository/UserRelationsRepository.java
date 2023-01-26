package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.UserRelationsPK;
import apirestful.iawebbackend.model.UsersRelations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRelationsRepository extends JpaRepository<UsersRelations, UserRelationsPK> { }
