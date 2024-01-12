package tenten.blooming.domain.subgoal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenten.blooming.domain.goal.entity.Goal;
import tenten.blooming.domain.goal.repository.GoalRepository;
import tenten.blooming.domain.subgoal.dto.SubgoalResponse;
import tenten.blooming.domain.subgoal.entity.Subgoal;
import tenten.blooming.domain.subgoal.repository.SubgoalRepository;
import tenten.blooming.domain.user.entity.User;
import tenten.blooming.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubgoalService {

    @Autowired private final UserRepository userRepository;
    @Autowired private final SubgoalRepository subgoalRepository;
    @Autowired private final GoalRepository goalRepository;
    /**
     * Subgoal 생성
     */
    @Transactional
    public Subgoal createSubgoal(Goal goal) {
        Subgoal subgoal = Subgoal.createSubgoal(goal);

        return subgoalRepository.save(subgoal);
    }

    /**
     * subgoal의 과제 한 개 완료
     */
    @Transactional
    public List<LocalDate> updateSubgoal(Long subgoalId) {
        Subgoal findSubgoal = subgoalRepository.findById(subgoalId).orElse(null);

        return findSubgoal.addDoneDate();
    }

    public List<Goal> getGoalByUserId(Long userId) {
        User findUser = userRepository.findById(userId).orElse(null);

        return findUser.getGoals();
    }

    public SubgoalResponse getSubgoalInfoByUserId(Long userId) {
        List<Goal> goals = getGoalByUserId(userId);
//        Goal goal = goals.get(goals.size() - 1);


        SubgoalResponse subgoalResponse = new SubgoalResponse();

        return subgoalResponse;
    }
}
