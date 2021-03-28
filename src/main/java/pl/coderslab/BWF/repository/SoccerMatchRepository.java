package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.SoccerMatch;
@Repository
public interface SoccerMatchRepository extends JpaRepository<SoccerMatch, Long> {

}
