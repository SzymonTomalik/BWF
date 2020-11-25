package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.BWF.entity.UserGroupAccount;

public interface UserGroupAccountRepository extends JpaRepository<UserGroupAccount, Long> {
}
