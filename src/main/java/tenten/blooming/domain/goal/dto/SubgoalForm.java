package tenten.blooming.domain.goal.dto;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SubgoalForm {

    private List<String> subGoalList;
}
