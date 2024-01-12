package tenten.blooming.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import tenten.blooming.domain.goal.entity.Goal;
import tenten.blooming.domain.user.repository.UserRepository;
import tenten.blooming.domain.user.dto.UserForm;
import tenten.blooming.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 로그인 id 중복 체크
    public boolean existsMember(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    public User join(UserForm dto) {

        // 이미 존재하는 아이디로 회원가입한 경우
        if (existsMember(dto.getLoginId())) {
            throw new DataIntegrityViolationException("이미 존재하는 아이디입니다.");
        }

        return userRepository.save(dto.toEntity());
    }

    public User login(String loginId, String password) {
        User findUser = userRepository.findByLoginId(loginId);

        if (findUser != null && findUser.getPassword().equals(password)) {
            return findUser;
        } else {
            throw new DataIntegrityViolationException("로그인에 실패하였습니다.");
        }
    }

    public Long getActiveGoalIdByLoginId(String loginId) {
        User findUser = userRepository.findByLoginId(loginId);

        List<Goal> goals = findUser.getGoals();
        Goal activeGoal = goals.get(goals.size() - 1);

        return activeGoal.getGoalId();
    }
}
