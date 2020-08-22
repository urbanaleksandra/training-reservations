package com.diplomski.service;

import com.diplomski.dto.NewTrainingDayDTO;
import com.diplomski.dto.TrainingDTO;

public interface TrainingService {

    TrainingDTO createNewTraining(NewTrainingDayDTO request);

    TrainingDTO updateTraining(NewTrainingDayDTO request, Long id);
}