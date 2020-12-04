package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.converter.SoccerMatchConverter;
import pl.coderslab.BWF.entity.SoccerMatch;
import pl.coderslab.BWF.webClient.matches.MatchesClient;

import java.util.List;
@Repository
public interface SoccerMatchRepository extends JpaRepository<SoccerMatch, Long> {

}
