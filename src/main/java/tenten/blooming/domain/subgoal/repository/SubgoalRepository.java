package tenten.blooming.domain.subgoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tenten.blooming.domain.subgoal.entity.Subgoal;

public interface SubgoalRepository extends JpaRepository<Subgoal, Long> {

}
