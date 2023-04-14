package apirestful.iawebbackend.repository;
import apirestful.iawebbackend.model.TextRelation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRelationRepository extends JpaRepository<TextRelation,Long> {
}
