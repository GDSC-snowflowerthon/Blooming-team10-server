package tenten.blooming.domain.subgoal.dto;

import lombok.Data;

@Data
public class UpdateSubgoalRequest {
    private Long goalId;
    private Long subgoalId;
}
