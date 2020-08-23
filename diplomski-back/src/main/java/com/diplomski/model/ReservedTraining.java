package com.diplomski.model;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause="deleted=0")
@Inheritance(strategy = InheritanceType.JOINED)
public class ReservedTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private boolean attended;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trainingDay_id", referencedColumnName = "id")
    private TrainingDay trainingDay;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "simpleUser_id", referencedColumnName = "id")
    private SimpleUser simpleUser;

    @Column
    private boolean deleted;

    @Column
    private String date;
}
