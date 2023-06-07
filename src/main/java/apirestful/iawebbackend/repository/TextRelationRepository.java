package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.QuestionaryGroup;
import apirestful.iawebbackend.model.Response;
import apirestful.iawebbackend.model.TextRelation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TextRelationRepository extends JpaRepository<TextRelation,Long> {

    @Query(value = "SELECT tr.relation_id,tr.question_id,tr.questionary_group_id FROM text_relation as tr WHERE tr.relation_id=?",nativeQuery = true)
    TextRelation getTRbyID(Long ID);


    @Query(value = "SELECT tr.relation_id,q.id QuestionID,q.text,q.type,r.id ResponseID,r.integer_value,r.text_value,r.text_relation_id,r.polls_assignment_id\n" +
            "FROM text_relation AS tr\n" +
            "JOIN questionary_group AS qg ON qg.id = tr.questionary_group_id\n" +
            "LEFT JOIN question AS q ON q.id = tr.question_id\n" +
            "LEFT JOIN response AS r ON r.text_relation_id = tr.relation_id\n" +
            "WHERE tr.questionary_group_id = ? \n" +
            "  AND (r.polls_assignment_id = ? OR r.polls_assignment_id IS NULL);", nativeQuery = true)
    List<Map<Question,Response>> getResponsebyQGbyUser(String qg_id, String polls_assignment_id);


}
