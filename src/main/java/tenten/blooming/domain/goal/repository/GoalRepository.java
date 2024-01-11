package tenten.blooming.domain.goal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tenten.blooming.domain.goal.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {


}

