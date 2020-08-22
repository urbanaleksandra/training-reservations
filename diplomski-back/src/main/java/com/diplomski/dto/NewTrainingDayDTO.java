package com.diplomski.dto;

import java.util.List;

public class NewTrainingDayDTO {

    private TrainingDTO training;

    private String trainer;

    private List<DayAndTimeOfTrainingDTO> daysOfTraining;

    public NewTrainingDayDTO(TrainingDTO training, String trainer, List<DayAndTimeOfTrainingDTO> daysOfTraining) {
        this.training = training;
        this.trainer = trainer;
        this.daysOfTraining = daysOfTraining;
    }

    public NewTrainingDayDTO() {
    }

    public TrainingDTO getTraining() {
        return training;
    }

    public void setTraining(TrainingDTO training) {
        this.training = training;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<DayAndTimeOfTrainingDTO> getDaysOfTraining() {
        return daysOfTraining;
    }

    public void setDaysOfTraining(List<DayAndTimeOfTrainingDTO> daysOfTraining) {
        this.daysOfTraining = daysOfTraining;
    }
}