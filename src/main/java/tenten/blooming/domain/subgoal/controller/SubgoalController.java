package tenten.blooming.domain.subgoal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tenten.blooming.domain.goal.entity.Goal;
import tenten.blooming.domain.goal.repository.GoalRepository;
import tenten.blooming.domain.subgoal.dto.GetSubgoalInfoByUserIdRequest;
import tenten.blooming.domain.subgoal.dto.SubgoalResponse;
import tenten.blooming.domain.subgoal.service.SubgoalService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubgoalController {

    @Autowired private final GoalRepository goalRepository;
    @Autowired private final SubgoalService subgoalService;
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
            @RequestBody @Validated GetSubgoalInfoByUserIdRequest request
        ) {
        SubgoalResponse subgoalResponse = subgoalService.getSubgoalInfoByUserId(request.getUserId());

        return ResponseEntity.ok(subgoalResponse);
    }
}
