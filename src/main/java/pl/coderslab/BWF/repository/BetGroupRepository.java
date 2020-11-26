package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.BWF.entity.BetGroup;

public interface BetGroupRepository extends JpaRepository<BetGroup, Long> {
}
