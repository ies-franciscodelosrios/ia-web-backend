package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Long> {
    @Query(value = "SELECT r.id,r.integer_value,r.text_value,r.text_relation_id FROM response as r WHERE r.id=?",nativeQuery = true)
    Response getResponseByID(Long ID);


    @Query(value = "SELECT r.id,r.integer_value,r.text_value,r.text_relation_id FROM response as r WHERE r.id=?",nativeQuery = true)
    Response getResponseByIDSurvey(Long ID);

}
