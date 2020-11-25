package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.BWF.entity.UserGroups;

public interface UserGroupsRepository extends JpaRepository<UserGroups, Long> {
}
