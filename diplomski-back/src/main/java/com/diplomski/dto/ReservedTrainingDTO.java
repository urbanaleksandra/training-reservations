package com.diplomski.dto;

public class ReservedTrainingDTO {

    private TrainingDayDTO trainingDay;

    private String simpleUser;

    public ReservedTrainingDTO() {
    }

    public ReservedTrainingDTO(TrainingDayDTO trainingDay, String simpleUser) {
        this.trainingDay = trainingDay;
        this.simpleUser = simpleUser;
    }

    public TrainingDayDTO getTrainingDay() {
        return trainingDay;
    }

    public void setTrainingDay(TrainingDayDTO trainingDay) {
        this.trainingDay = trainingDay;
    }

    public String getSimpleUser() {
        return simpleUser;
    }

    public void setSimpleUser(String simpleUser) {
        this.simpleUser = simpleUser;
    }
}
