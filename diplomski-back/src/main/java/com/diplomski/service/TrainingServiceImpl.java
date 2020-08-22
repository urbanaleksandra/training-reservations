package com.diplomski.service;

import com.diplomski.dto.NewTrainingDayDTO;
import com.diplomski.dto.TrainingDTO;
import com.diplomski.model.Training;
import com.diplomski.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingDayService trainingDayService;

    @Override
    public TrainingDTO createNewTraining(NewTrainingDayDTO request) {
        Training training = new Training();
        training.setDeleted(false);
        training.setCapacity(request.getTraining().getCapacity());
        training.setDescription(request.getTraining().getDescription());
        training.setDuration(request.getTraining().getDuration());
        training.setName(request.getTraining().getName());
        training = trainingRepository.save(training);
        request.getTraining().setId(training.getId());

        trainingDayService.createTrainingDays(training, request.getDaysOfTraining(), request.getTrainer());

        return request.getTraining();
    }

    @Override
    public TrainingDTO updateTraining(NewTrainingDayDTO request, Long id) {
        Training training = trainingRepository.getOne(id);
        training.setDeleted(false);
        training.setCapacity(request.getTraining().getCapacity());
        training.setDescription(request.getTraining().getDescription());
        training.setDuration(request.getTraining().getDuration());
        training.setName(request.getTraining().getName());
        trainingRepository.save(training);

        trainingDayService.updateTrainingDay(training, request.getDaysOfTraining(), request.getTrainer());

        return request.getTraining();
    }


}