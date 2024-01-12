package tenten.blooming.domain.subgoal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tenten.blooming.domain.subgoal.dto.CompletedGoalInfoResponse;
import tenten.blooming.domain.subgoal.dto.ResponseUpdateSubgoal;
import tenten.blooming.domain.subgoal.dto.SubgoalResponse;
import tenten.blooming.domain.subgoal.entity.Subgoal;
import tenten.blooming.domain.subgoal.repository.SubgoalRepository;
import tenten.blooming.domain.subgoal.service.SubgoalService;
import tenten.blooming.domain.user.entity.User;
import tenten.blooming.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubgoalController {

    private final SubgoalService subgoalService;
    private final SubgoalRepository subgoalRepository;
    private final UserRepository userRepository;

    @PostMapping("/subgoal/{goalId}/detail/{subgoalId}")
    public ResponseEntity<ResponseUpdateSubgoal> updateSubgoal(
            @PathVariable("goalId") Long goalId,
            @PathVariable("subgoalId") Long subgoalId
    ) {
        Subgoal findSubgoal = subgoalRepository.findById(subgoalId).orElse(null);
        ResponseUpdateSubgoal responseUpdateSubgoal = new ResponseUpdateSubgoal();

        responseUpdateSubgoal.setSubgoalName(findSubgoal.getSubgoalName());

        List<LocalDate> doneDates = subgoalService.updateSubgoal(subgoalId);
        responseUpdateSubgoal.setDoneDateList(doneDates);

        return ResponseEntity.ok(responseUpdateSubgoal);
    }

    @GetMapping("/subgoal/{userId}/progress")
    public ResponseEntity<SubgoalResponse> getSubgoalInfoByUserId(@PathVariable Long userId) {
        SubgoalResponse subgoalResponse = subgoalService.getSubgoalInfoByUserId(userId);

        User findUser = userRepository.findById(userId).orElse(null);
        subgoalResponse.setNickname(findUser.getNickname());

        return ResponseEntity.ok(subgoalResponse);
    }

    @GetMapping("/snows/{userId}")
    public ResponseEntity<CompletedGoalInfoResponse> getCompletedGoalInfos(@PathVariable Long userId)
    {
        CompletedGoalInfoResponse completedGoalInfoResponse = subgoalService.getCompletedGoalInfo(userId);

        return ResponseEntity.ok(completedGoalInfoResponse);
    }
}
