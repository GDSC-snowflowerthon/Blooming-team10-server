package tenten.blooming.domain.goal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenten.blooming.domain.goal.dto.GoalDto;
import tenten.blooming.domain.goal.entity.Goal;
import tenten.blooming.domain.goal.repository.GoalRepository;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    public Goal create(GoalDto dto) {
        Goal goal = dto.toEntity();

        return goalRepository.save(goal);
    }
}
