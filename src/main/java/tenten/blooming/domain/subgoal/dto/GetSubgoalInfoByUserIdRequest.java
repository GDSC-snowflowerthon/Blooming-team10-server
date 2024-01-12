package tenten.blooming.domain.subgoal.dto;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSubgoalInfoByUserIdRequest {
    private Long userId;
}
