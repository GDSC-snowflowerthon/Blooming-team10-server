package tenten.blooming.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Integer code;
    private String message;
    private LoginResult result;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginResult {
        private Long userId;
        private String loginId;
        private String nickname;
        private String password;
        private Boolean hasGoal = false;
        private Timestamp createdAt;
        private Long activeGoalId;
    }

}
