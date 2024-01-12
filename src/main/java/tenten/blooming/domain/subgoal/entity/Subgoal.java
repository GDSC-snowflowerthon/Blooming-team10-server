package tenten.blooming.domain.subgoal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tenten.blooming.domain.goal.entity.Goal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "subgoal")
public class Subgoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subgoal_id", nullable = false)
    private Long subgoalId; // 기본키

    @Column(name = "subgoal_name", nullable = false)
    private String subgoalName;

    private LocalDate doneDate1;
    private LocalDate doneDate2;
    private LocalDate doneDate3;
    private LocalDate doneDate4;
    private LocalDate doneDate5;
    private LocalDate doneDate6;
    private LocalDate doneDate7;
    private LocalDate doneDate8;
    private LocalDate doneDate9;
    private LocalDate doneDate10;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    //==생성 메서드=//
    public static Subgoal createSubgoal(Goal goal) {
        Subgoal subgoal = new Subgoal();
        subgoal.setGoal(goal);
        subgoal.setSubgoalName("작성 중");

        return subgoal;
    }

    //==비즈니스 로직==//
    /**
     * 해당 subgoal의 과제 체크 정보(doneDates 리스트) 반환
     */
    public List<LocalDate> getDoneDates() {
        List<LocalDate> doneDates= Arrays.asList(doneDate1, doneDate2, doneDate3, doneDate4, doneDate5, doneDate6, doneDate7, doneDate8, doneDate9, doneDate10);

        return doneDates;
    }

    /**
     * doneDate를 현재 날짜로 입력하고, 업데이트된 doneDates 리스트 반환
     * @return doneDateCnt
     */
    public List<LocalDate> addDoneDate() {
        List<LocalDate> doneDates = getDoneDates();

        for(int i = 0; i < doneDates.size(); i++) {
            if(doneDates.get(i) == null) {
                if(i != 0 && (doneDates.get(i-1) == LocalDate.now())) {
                    throw new IllegalStateException("이미 체크된 TASK입니다.");
                }
                doneDates.set(i, LocalDate.now());
                break;
            }
        }

        return doneDates;
    }
}
