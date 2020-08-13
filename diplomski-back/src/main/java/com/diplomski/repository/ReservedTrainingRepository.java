package com.diplomski.repository;

import com.diplomski.model.ReservedTraining;
import com.diplomski.model.SimpleUser;
import com.diplomski.model.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservedTrainingRepository extends JpaRepository<ReservedTraining, Long> {

    Optional<ReservedTraining> findById(Long id);

    List<ReservedTraining> findAll();

    List<ReservedTraining> findBySimpleUser(SimpleUser simpleUser);

    List<ReservedTraining> findByTrainingDay(TrainingDay trainingDay);
}
