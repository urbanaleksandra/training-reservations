package com.diplomski.repository;

import com.diplomski.model.Training;
import com.diplomski.model.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {

    List<TrainingDay> findAll();

    Optional<TrainingDay> findById(Long id);

    List<TrainingDay> findByTraining(Training training);
}
