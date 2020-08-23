package com.diplomski.repository;

import com.diplomski.model.Training;
import com.diplomski.model.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {

    List<TrainingDay> findAll();

    Optional<TrainingDay> findById(Long id);

    List<TrainingDay> findByTraining(Training training);
}
