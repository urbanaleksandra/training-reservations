package com.diplomski.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause="deleted=0")
@Table(name = "simple_user")
public class SimpleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    private String cardNumber;

    @Column
    private LocalDate dateOfLastPayment;

    @Column
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trainingPackage_id", referencedColumnName = "id")
    private TrainingPackage trainingPackage;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.REFRESH)
    private Set<ReservedTraining> reservedTrainings = new HashSet<>();
}
