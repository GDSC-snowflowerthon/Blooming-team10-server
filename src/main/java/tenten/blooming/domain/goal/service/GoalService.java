package tenten.blooming.domain.goal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenten.blooming.domain.goal.dto.GoalDto;
import tenten.blooming.domain.goal.entity.Goal;
import tenten.blooming.domain.goal.repository.GoalRepository;
import tenten.blooming.domain.subgoal.entity.Subgoal;
import tenten.blooming.domain.subgoal.repository.SubgoalRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private SubgoalRepository subgoalRepository;

    public Goal createGoal(GoalDto dto) {
        Goal goal = dto.toEntity();

        return goalRepository.save(goal);
    }


    public List<Subgoal> saveSubgoal(List<String> subgoalList, Long goalId) {
        List<Subgoal> createdSubgoals = new ArrayList<>();

        Goal findGoal = goalRepository.findById(goalId).orElse(null);

        if (findGoal != null) {
            for (String subgoalName : subgoalList) {
                Subgoal subgoal = new Subgoal();
                subgoal.setSubgoalName(subgoalName);

                Goal goal = new Goal();
                goal.setGoalId(goalId);
                goal.setGoalName(findGoal.getGoalName());
                goal.setCreatedAt(findGoal.getCreatedAt());
                goal.setIsActivate(findGoal.getIsActivate());
                subgoal.setGoal(goal);

                Subgoal createdSubgoal = subgoalRepository.save(subgoal);
                createdSubgoals.add(createdSubgoal);
            }
        }

        return createdSubgoals;
    }
}

