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


    @Query(value = "SELECT q.text,r.text_value AS respuesta FROM response as r \n" +
            "JOIN text_relation as tr ON r.text_relation_id=tr.relation_id \n" +
            "JOIN questionary_group as qg ON qg.id=tr.questionary_group_id\n" +
            "JOIN question as q ON q.id = tr.question_id\n" +
            "JOIN polls_assignment as pa ON pa.questionary_group_id=qg.id  AND pa.id=r.polls_assignment_id\n" +
            "WHERE qg.id=? AND pa.id_navision=?",nativeQuery = true)
    List<Map<Question,Response>> getResponsebyQGbyUser(Long qg_id, String ID_navision);


}
