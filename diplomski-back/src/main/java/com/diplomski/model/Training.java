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
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int capacity;

    @Column
    private Long duration;

    @OneToMany(cascade = CascadeType.REFRESH)
    private Set<TrainingDay> trainingDays = new HashSet<>();

    @Column
    private boolean deleted;
}
