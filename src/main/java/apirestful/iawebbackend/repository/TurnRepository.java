package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.Event;
import apirestful.iawebbackend.model.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> { }
