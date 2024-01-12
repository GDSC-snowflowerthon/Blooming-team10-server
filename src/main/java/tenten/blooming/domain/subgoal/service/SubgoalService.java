package tenten.blooming.domain.subgoal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenten.blooming.domain.goal.entity.Goal;
import tenten.blooming.domain.subgoal.dto.CompletedGoalInfoResponse;
import tenten.blooming.domain.subgoal.dto.SubgoalResponse;
import tenten.blooming.domain.subgoal.entity.Subgoal;
import tenten.blooming.domain.subgoal.repository.SubgoalRepository;
import tenten.blooming.domain.user.entity.User;
import tenten.blooming.domain.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubgoalService {

    private final UserRepository userRepository;
    private final SubgoalRepository subgoalRepository;

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

        return addDoneDate(subgoalId);
    }

    /**
     * user의 모든 goal을 반환한다.
     */
    public List<Goal> getGoalByUserId(Long userId) {
        User findUser = userRepository.findById(userId).orElse(null);

        return findUser.getGoals();
    }

    /**
     * goalName, goalId, goalCreateDate, subgoalList 반환
     */
    public SubgoalResponse getGoalInfo(Goal goal) {
        List<Subgoal> subgoals = goal.getSubgoals();
        List<SubgoalResponse.SubgoalInfo> subgoalInfoList = new ArrayList<>();

        SubgoalResponse subgoalResponse = new SubgoalResponse();

        for(Subgoal subgoal : subgoals) {
            SubgoalResponse.SubgoalInfo subgoalInfo = new SubgoalResponse.SubgoalInfo();
            subgoalInfo.setSubgoalId(subgoal.getSubgoalId());
            subgoalInfo.setSubgoalName(subgoal.getSubgoalName());
            subgoalInfo.setDoneDateList(subgoal.getDoneDates());
            subgoalInfoList.add(subgoalInfo);
        }

        subgoalResponse.setGoalName(goal.getGoalName());
        subgoalResponse.setGoalId(goal.getGoalId());
        subgoalResponse.setGoalCreateDate(goal.getCreatedAt());
        subgoalResponse.setSubgoalList(subgoalInfoList);

        return subgoalResponse;
    }

    /**
     * 해당 유저의 활성화된 goal 정보 가져오기
     */
    public SubgoalResponse getSubgoalInfoByUserId(Long userId) {
        //userId의 모든 goal 받아오기
        List<Goal> goals = getGoalByUserId(userId);

        User findUser = userRepository.findById(userId).orElse(null);

        if(!findUser.getHasGoal()) { throw new IllegalStateException("활성화되어 있는 목표가 없습니다."); }

        //해당 유저의 활성화된 goal 가져오기
        Goal goal = goals.get(goals.size() - 1);

        //goal의 정보 가져오기
        return getGoalInfo(goal);
    }

    public CompletedGoalInfoResponse getCompletedGoalInfo(Long userId) {
        List<Goal> goals = getGoalByUserId(userId);
        User user = userRepository.findById(userId).orElse(null);

        CompletedGoalInfoResponse completedGoalInfoResponse = new CompletedGoalInfoResponse();

        List<SubgoalResponse> goalList = new ArrayList<>();

        for(Goal goal : goals) {
            goalList.add(getGoalInfo(goal));
        }

        if(!goalList.isEmpty() && (user.getHasGoal())) {
            goalList.remove(goalList.size() - 1);
        }

        //completedGoalInfoResponse에 추가
        completedGoalInfoResponse.setGoalList(goalList);

        return completedGoalInfoResponse;
    }

    /**
     * doneDate를 현재 날짜로 입력하고, 업데이트된 doneDates 리스트 반환
     * @return doneDateCnt
     */
    public List<LocalDate> addDoneDate(Long subgoalId) {
        Subgoal findSubgoal = subgoalRepository.findById(subgoalId).orElse(null);
        List<LocalDate> doneDates = findSubgoal.getDoneDates();
        final LocalDate today = LocalDate.now();

        for(int i = 0; i < doneDates.size(); i++) {
            if(doneDates.get(i) == null) {
                if(i > 0 && (Objects.equals(doneDates.get(i - 1), today))) {
                    throw new IllegalStateException("이미 체크된 TASK입니다.");
                }
                switch (i + 1) {
                    case 1: {
                        findSubgoal.setDoneDate1(today);
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 2: {
                        findSubgoal.setDoneDate2(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 3: {
                        findSubgoal.setDoneDate3(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 4: {
                        findSubgoal.setDoneDate4(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 5: {
                        findSubgoal.setDoneDate5(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 6: {
                        findSubgoal.setDoneDate6(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 7: {
                        findSubgoal.setDoneDate7(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 8: {
                        findSubgoal.setDoneDate8(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 9: {
                        findSubgoal.setDoneDate9(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                    case 10: {
                        findSubgoal.setDoneDate10(LocalDate.now());
                        subgoalRepository.save(findSubgoal);
                        break;
                    }
                }
                break;
            }
        }
        return doneDates;
    }
}
