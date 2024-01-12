package tenten.blooming.domain.subgoal.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUpdateSubgoal {
    private List<LocalDate> doneDateList;
    private String subgoalName;
    private String errorMsg = null;
}
