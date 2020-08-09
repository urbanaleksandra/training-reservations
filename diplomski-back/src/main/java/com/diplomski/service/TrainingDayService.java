package com.diplomski.service;

import com.diplomski.dto.WeekTrainingDTO;
import com.diplomski.model.TrainingDay;

import java.util.List;
import java.util.Optional;

public interface TrainingDayService {

    Optional<TrainingDay> findById(Long id);

    List<TrainingDay> findAll();

    List<WeekTrainingDTO> getAll();
}
