package tenten.blooming.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tenten.blooming.domain.user.entity.User;

@Data
@AllArgsConstructor
@Builder
public class UserForm {
    private Long userId;

    private String loginId;
    private String nickname;
    private String password;

    public User toEntity() {
        User user = new User();

        user.setLoginId(loginId);
        user.setNickname(nickname);
        user.setPassword(password);

        return user;
    }
}
