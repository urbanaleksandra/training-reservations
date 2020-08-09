package com.diplomski.dto;

public class TrainingDayDTO {

    private Long id;

    private String day;

    private TrainingDTO training;

    private String trainer;

    private String startsAt;

    public TrainingDayDTO(Long id, String day, TrainingDTO training, String trainer, String startsAt) {
        this.id = id;
        this.day = day;
        this.training = training;
        this.trainer = trainer;
        this.startsAt = startsAt;
    }

    public TrainingDayDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(String startsAt) {
        this.startsAt = startsAt;
    }
}
