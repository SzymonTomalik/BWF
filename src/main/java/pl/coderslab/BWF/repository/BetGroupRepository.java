package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.BetGroup;
@Repository
public interface BetGroupRepository extends JpaRepository<BetGroup, Long> {
}
