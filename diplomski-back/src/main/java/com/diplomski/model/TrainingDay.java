package com.diplomski.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause="deleted=0")
@Table(name = "training_day")
public class TrainingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    private Day day;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id", referencedColumnName = "id")
    private Training training;

    @Column
    private String trainer;

    @Column
    private LocalTime startsAt;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "trainingDay")
    private Set<ReservedTraining> reservedTrainings = new HashSet<>();

    @Column
    private boolean deleted;

}
