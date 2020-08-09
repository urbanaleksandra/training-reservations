package com.diplomski.model;

import com.diplomski.enums.PackageType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause="deleted=0")
@Table(name = "training_package")
public class TrainingPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private PackageType packageType;

    @Column
    private int monthlyPrice;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "trainingPackage")
    private List<SimpleUser> simpleUsers;

    @Column
    private boolean deleted;

}
