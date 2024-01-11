package tenten.blooming.domain.goal.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private final int n = 1;
    private double temperature;

    public ChatRequest(String model, String goalName) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", "입력 받은 큰 목표 G를 달성하기 위한 구체적인 6가지 세부 목표 g1, g2, g3, g4, g5, g6 을 알려 주는 assistant bot. 6가지 세부 목표는 10일동안 매일매일 꾸준히 실천하여 10일 뒤에 G를 달성할 수 있는 목표여야 함."));
        this.messages.add(new Message("user", "G: " + goalName));
    }

}
