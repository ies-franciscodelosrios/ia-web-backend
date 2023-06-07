package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM user u WHERE u.login LIKE :login", nativeQuery = true)
    User getByIdNavision(@Param("login") String login);

    @Query(value = "SELECT * FROM user u WHERE u.codigo LIKE :codigo",nativeQuery = true)
    User getByCodigo(@Param("codigo") String codigo);

    @Query(value = "DELETE FROM USER AS a WHERE a.codigo LIKE ?",nativeQuery = true)
    User deleteByCodigo(String codigo);


}

