package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.BWF.entity.SoccerMatch;

public interface SoccerMatchRepository extends JpaRepository<SoccerMatch, Long> {
}
