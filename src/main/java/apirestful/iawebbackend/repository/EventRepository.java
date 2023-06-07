package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT e.* FROM event e WHERE e.assign_by_user_id = :userId " +
            "UNION " +
            "SELECT e.* FROM event e WHERE e.user_id = :userId", nativeQuery = true)
    List<Event> findEventsByUserId(@Param("userId") String userId);
}