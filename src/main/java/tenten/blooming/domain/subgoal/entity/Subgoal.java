package tenten.blooming.domain.subgoal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tenten.blooming.domain.goal.entity.Goal;

import java.time.LocalDate;

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

}
