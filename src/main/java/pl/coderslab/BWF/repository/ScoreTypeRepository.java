package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.BWF.entity.ScoreType;

public interface ScoreTypeRepository extends JpaRepository<ScoreType, Long> {
}
