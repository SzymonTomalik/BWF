package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.BWF.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
