package tenten.blooming.domain.subgoal.dto;

import lombok.*;

import java.util.List;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedGoalInfoResponse {
    private List<SubgoalResponse> goalList;
}
