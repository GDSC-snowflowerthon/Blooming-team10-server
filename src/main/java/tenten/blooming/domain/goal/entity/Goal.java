package tenten.blooming.domain.goal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import tenten.blooming.domain.subgoal.entity.Subgoal;
import tenten.blooming.domain.user.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id", nullable = false)
    private Long goalId; // 기본키

    @Column(name = "goal_name", nullable = false)
    private String goalName;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "is_activate")
    private Boolean isActivate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "goal")
    private List<Subgoal> subgoals = new ArrayList<>();


}
