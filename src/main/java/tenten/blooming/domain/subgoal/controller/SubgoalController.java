package tenten.blooming.domain.subgoal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tenten.blooming.domain.goal.entity.Goal;
import tenten.blooming.domain.goal.repository.GoalRepository;
import tenten.blooming.domain.subgoal.dto.CompletedGoalInfoResponse;
import tenten.blooming.domain.subgoal.dto.GetUserIdRequest;
import tenten.blooming.domain.subgoal.dto.SubgoalResponse;
import tenten.blooming.domain.subgoal.entity.Subgoal;
import tenten.blooming.domain.subgoal.repository.SubgoalRepository;
import tenten.blooming.domain.subgoal.service.SubgoalService;
import tenten.blooming.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubgoalController {

    @Autowired private final GoalRepository goalRepository;
    @Autowired private final SubgoalService subgoalService;
    @Autowired private final UserRepository userRepository;
    @Autowired private final SubgoalRepository subgoalRepository;

    @PostMapping("/subgoal/{goalId}/detail/{subgoalId}")
    public ResponseEntity<List<LocalDate>> updateSubgoal(
            @PathVariable("goalId") Long goalId,
            @PathVariable("subgoalId") Long subgoalId
    ) {
        List<LocalDate> doneDates = subgoalService.updateSubgoal(subgoalId);

        return ResponseEntity.ok(doneDates);
    }


    @GetMapping("/subgoal/{goalId}/progress")
    public ResponseEntity<SubgoalResponse> getSubgoalInfoByUserId(
            @PathVariable Long goalId,
            @RequestBody @Validated GetUserIdRequest request
        ) {
        SubgoalResponse subgoalResponse = subgoalService.getSubgoalInfoByUserId(request.getUserId());

        return ResponseEntity.ok(subgoalResponse);
    }

    @GetMapping("/snows")
    public ResponseEntity<CompletedGoalInfoResponse> getCompletedGoalInfos(
            @RequestBody @Validated GetUserIdRequest request)
    {
        CompletedGoalInfoResponse completedGoalInfoResponse = subgoalService.getCompletedGoalInfo(request.getUserId());

        return ResponseEntity.ok(completedGoalInfoResponse);
    }
}
