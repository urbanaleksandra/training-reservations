package com.diplomski.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause="deleted=0")
@Table(name = "days")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String day;

    @OneToMany(cascade = CascadeType.REFRESH)
    private Set<TrainingDay> trainingDays = new HashSet<>();

    @Column
    private boolean deleted;
}
