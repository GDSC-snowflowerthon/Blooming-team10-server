package tenten.blooming.domain.goal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tenten.blooming.domain.goal.dto.GoalDto;
import tenten.blooming.domain.goal.entity.Goal;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}

