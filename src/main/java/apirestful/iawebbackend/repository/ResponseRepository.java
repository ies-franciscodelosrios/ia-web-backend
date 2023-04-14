package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Long> {
}
