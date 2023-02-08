package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.model.UserRelationsPK;
import apirestful.iawebbackend.model.UsersRelations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationsRepository extends JpaRepository<UsersRelations, UserRelationsPK> {

    @Query(value = "SELECT COUNT(*) FROM users_relations WHERE users_relations.id_navision LIKE ?",nativeQuery = true)
    String getActivateRelations(String idnavision);


    @Query(value = "SELECT id_navision2 FROM users_relations WHERE users_relations.id_navision LIKE ?",nativeQuery = true)
    List<String> getActivateRelationsName(String idnavision);
}

