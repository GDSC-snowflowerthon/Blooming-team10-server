package tenten.blooming.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tenten.blooming.domain.user.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginId(String loginId);
    User findByLoginId(String loginId);
}
