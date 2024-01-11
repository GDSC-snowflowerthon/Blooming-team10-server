package tenten.blooming.domain.goal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import tenten.blooming.domain.goal.entity.Goal;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class GoalDto {
    private Long userId;
    private String goalName;

    public Goal toEntity() {
        Goal goal = new Goal();

        goal.setGoalName(goalName);
        goal.setCreatedAt(LocalDate.now());
        goal.setIsActivate(true);

        return goal;
    }
}
