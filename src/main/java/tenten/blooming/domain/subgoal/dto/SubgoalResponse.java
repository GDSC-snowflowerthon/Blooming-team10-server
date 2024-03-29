package tenten.blooming.domain.subgoal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Getter @Setter
public class SubgoalResponse {
    private String goalName;
    private Long goalId;
    private LocalDate goalCreateDate;
    private List<SubgoalInfo> SubgoalList;
    private String nickname;

    @Data
    @NoArgsConstructor
    public static class SubgoalInfo {
        private Long subgoalId;
        private String subgoalName;
        private List<LocalDate> doneDateList;
    }
}



