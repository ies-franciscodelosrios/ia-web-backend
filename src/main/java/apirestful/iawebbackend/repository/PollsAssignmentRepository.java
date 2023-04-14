package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.PollsAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollsAssignmentRepository extends JpaRepository<PollsAssignment,Long> {
}
