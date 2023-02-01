package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    @Query(value = "SELECT user_id FROM user_rols WHERE rol_id = ?1", nativeQuery = true)
    List<String> userByRol(Long rolId);
}
