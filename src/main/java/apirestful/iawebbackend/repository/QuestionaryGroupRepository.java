package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.QuestionaryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionaryGroupRepository extends JpaRepository<QuestionaryGroup,Long> {
    @Query(value = "SELECT qg.id,qg.name,qg.active,qg.description_qg, qg.person_category, qg.start_date, qg.end_date FROM questionary_group as qg WHERE qg.id=?",nativeQuery = true)
    QuestionaryGroup getQuestionaryGroupbyID(Long ID);
}
