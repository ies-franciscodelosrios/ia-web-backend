package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.PollsAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollsAssignmentRepository extends JpaRepository<PollsAssignment,Long> {
    @Query(value = "SELECT pa.*" +
            " FROM polls_assignment AS pa" +
            " JOIN questionary_group as qg ON pa.questionary_group_id = qg.id" +
            " JOIN poll as po ON po.polls_assignment_id = pa.id" +
            " WHERE pa.id_navision = ? AND pa.active = 1 AND qg.active = 1 AND po.active = 1", nativeQuery = true)
    List<PollsAssignment> pollsAssignmentsByUser(String idNavision);

}
