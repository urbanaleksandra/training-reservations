package com.diplomski.service;

import com.diplomski.dto.DayAndTimeOfTrainingDTO;
import com.diplomski.dto.TrainingDayDTO;
import com.diplomski.dto.WeekTrainingDTO;
import com.diplomski.model.Training;
import com.diplomski.model.TrainingDay;

import java.util.List;
import java.util.Optional;

public interface TrainingDayService {

    Optional<TrainingDay> findById(Long id);

    List<TrainingDay> findAll();

    List<WeekTrainingDTO> getAll();

    void createTrainingDays(Training training, List<DayAndTimeOfTrainingDTO> daysOfTraining, String trainer);

    void updateTrainingDay(Training training, List<DayAndTimeOfTrainingDTO> daysOfTraining, String trainer);

    List<TrainingDayDTO> findAllByTraining(Long id);
}
