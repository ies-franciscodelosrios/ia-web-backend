package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.Question;
import apirestful.iawebbackend.model.QuestionaryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value = "SELECT CQG.id,CQG.name,CQG.description FROM category_questionary_group as CQG WHERE CQG.id LIKE ?",nativeQuery = true)
    List<Question> getQuestionsBySurvey(Long questionary_group_id);

    @Query(value = "SELECT q.id,q.text,q.type FROM question as q WHERE q.id=?",nativeQuery = true)
    Question getQuestionbyID(Long ID);

    @Query(value = "SELECT q.id,q.text,q.type FROM text_relation AS tr JOIN question as q ON tr.question_id=q.id JOIN questionary_group as qg ON qg.id=tr.questionary_group_id WHERE tr.questionary_group_id=? GROUP BY (tr.question_id)",nativeQuery = true)
    List<Question> getAllQuestionsBySurvey(Long ID);
}
