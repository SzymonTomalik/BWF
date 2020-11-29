package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.UserGroupAccount;
@Repository
public interface UserGroupAccountRepository extends JpaRepository<UserGroupAccount, Long> {
}
