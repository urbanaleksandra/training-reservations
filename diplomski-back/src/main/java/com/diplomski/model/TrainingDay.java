package com.diplomski.model;

import lombok.*;

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
public class TrainingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    private Day day;

    @ManyToOne
    private Training training;

    @Column
    private LocalTime startAt;

    @OneToMany(cascade = CascadeType.REFRESH)
    private Set<ReservedTraining> reservedTrainings = new HashSet<>();

}
