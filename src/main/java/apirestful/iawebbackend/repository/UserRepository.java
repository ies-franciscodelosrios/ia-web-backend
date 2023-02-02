package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //Consulta para buscar a los ninos por el nombre (filtro de busqueda)
    @Query(value = "SELECT * FROM USER WHERE login LIKE ?",nativeQuery = true)
    User getByIdNavision(String user_name);


    @Query(value = "SELECT * FROM USER AS a WHERE a.codigo LIKE ?",nativeQuery = true)
    User getByCodigo(String codigo);

    @Query(value = "DELETE FROM USER AS a WHERE a.codigo LIKE ?",nativeQuery = true)
    User deleteByCodigo(String codigo);

    @Query(value = "SELECT login FROM USER WHERE login LIKE ?",nativeQuery = true)
    boolean existsByNombre(@Param("login") String login);

}

