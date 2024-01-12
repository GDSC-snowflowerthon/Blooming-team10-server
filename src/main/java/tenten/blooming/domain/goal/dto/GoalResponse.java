package tenten.blooming.domain.goal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoalResponse {
    private Integer code;
    private String message;
    private GoalResult result;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GoalResult {
        private Long goalId;
        private String goalName;
        private List<String> subgoalList;
    }
}

