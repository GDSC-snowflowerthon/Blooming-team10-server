package tenten.blooming.domain.goal.controller;
import jakarta.persistence.Basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import tenten.blooming.domain.goal.dto.ChatRequest;
import tenten.blooming.domain.goal.dto.ChatResponse;
import tenten.blooming.domain.goal.dto.GoalDto;
import tenten.blooming.domain.goal.entity.Goal;
import tenten.blooming.domain.goal.service.GoalService;
import tenten.blooming.global.common.BasicResponse;

@RestController
public class GoalController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GoalService goalService;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping("/goal")
    public ResponseEntity<BasicResponse> createGoal(@RequestBody GoalDto dto) {
        Goal created = goalService.create(dto);
        BasicResponse basicResponse = new BasicResponse();

        if (created != null) {
            basicResponse = BasicResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("목표 등록에 성공했습니다.")
                    .result(created)
                    .build();

        }

        return new ResponseEntity<>(basicResponse, HttpStatus.OK);

    }

    @GetMapping("/goal")
    public String getSubGoal(@RequestParam String goalName) {
        ChatRequest request = new ChatRequest(model, goalName);

        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        return response.getChoices().get(0).getMessage().getContent();
    }
}