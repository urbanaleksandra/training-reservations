package com.diplomski.dto;

public class ReservedTrainingDTO {

    private TrainingDayDTO trainingDay;

    private String simpleUser;

    private String date;

    public ReservedTrainingDTO() {
    }

    public ReservedTrainingDTO(TrainingDayDTO trainingDay, String simpleUser, String date) {
        this.trainingDay = trainingDay;
        this.simpleUser = simpleUser;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
