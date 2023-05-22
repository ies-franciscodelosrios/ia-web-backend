package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.QuestionaryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionaryGroupRepository extends JpaRepository<QuestionaryGroup,Long> {
    @Query(value = "SELECT qg.id," +
            " qg.name," +
            " qg.active," +
            " qg.description_qg," +
            " qg.person_category," +
            " qg.start_date," +
            " qg.end_date" +
            " FROM questionary_group as qg" +
            " WHERE qg.id = ?", nativeQuery = true)
    QuestionaryGroup getQuestionaryGroupbyID(Long ID);

    @Query(value = "SELECT qg.id," +
            " qg.name," +
            " qg.active," +
            " qg.description_qg," +
            " qg.person_category," +
            " qg.start_date," +
            " qg.end_date" +
            " FROM questionary_group as qg" +
            " WHERE qg.active = 0", nativeQuery = true)
    List<QuestionaryGroup> getAllInactiveQuestionaryGroup();

    @Query(value = "SELECT qg.*" +
            " FROM polls_assignment AS pa" +
            " JOIN questionary_group as qg" +
            " ON pa.questionary_group_id = qg.id" +
            " WHERE pa.id_navision = ? AND pa.active = 1 AND qg.active = 1", nativeQuery = true)
    List<QuestionaryGroup> questionaryGroupsByUser(String idNavision);

    @Query(value = "SELECT * FROM questionary_group WHERE name = ?", nativeQuery = true)
    QuestionaryGroup findByName(String name);
}
