package com.diplomski.dto;

public class DayAndTimeOfTrainingDTO {

    private Long id;

    private String day;

    private String time;

    public DayAndTimeOfTrainingDTO(String day, String time, Long id) {
        this.day = day;
        this.time = time;
        this.id = id;
    }

    public DayAndTimeOfTrainingDTO() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}