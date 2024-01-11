package tenten.blooming.domain.goal.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Message {
    private String role;
    private String content;
}
