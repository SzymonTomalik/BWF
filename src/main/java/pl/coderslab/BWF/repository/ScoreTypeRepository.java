package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.ScoreType;
@Repository
public interface ScoreTypeRepository extends JpaRepository<ScoreType, Long> {
}
