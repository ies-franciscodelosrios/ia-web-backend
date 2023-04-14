package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.QuestionaryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionaryGroupRepository extends JpaRepository<QuestionaryGroup,Long> {
    @Query(value = "SELECT * FROM questionary_group",nativeQuery = true)
    List<QuestionaryGroup> getTODO();
}
