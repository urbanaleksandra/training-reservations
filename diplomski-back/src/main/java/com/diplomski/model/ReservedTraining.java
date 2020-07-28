package com.diplomski.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ReservedTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private boolean attended;

    @ManyToOne
    private TrainingDay trainingDay;

    @ManyToOne
    private SimpleUser simpleUser;
}
