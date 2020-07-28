package com.diplomski.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String name;

    @Column
    protected String description;

    @Column
    protected String trainer;

    @Column
    protected Long duration;

    @OneToMany(cascade = CascadeType.REFRESH)
    private Set<TrainingDay> trainingDays = new HashSet<>();
}
