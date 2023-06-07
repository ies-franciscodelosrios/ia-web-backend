package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Long> {
    @Query(value = "SELECT r.id,r.integer_value,r.text_value,r.text_relation_id FROM response as r WHERE r.id=?",nativeQuery = true)
    Response getResponseByID(Long ID);


    @Query(value = "SELECT r.id,r.integer_value,r.text_value,r.text_relation_id FROM response as r WHERE r.id=?",nativeQuery = true)
    Response getResponseByIDSurvey(Long ID);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO response(integer_value, text_value, text_relation_id, polls_assignment_id) VALUES (?,?,?,?)",nativeQuery = true)
    void createResponse(int integer_value,String text_value,String text_relation_id,String polls_assignment_id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE response SET integer_value=?,text_value=?,text_relation_id=?,polls_assignment_id=? WHERE id=?",nativeQuery = true)
    void updateReponse(int integer_value,String text_value,String text_relation_id,String polls_assignment_id,Long id);

}
