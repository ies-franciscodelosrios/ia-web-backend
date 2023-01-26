package apirestful.iawebbackend.repository;

import apirestful.iawebbackend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> { }
