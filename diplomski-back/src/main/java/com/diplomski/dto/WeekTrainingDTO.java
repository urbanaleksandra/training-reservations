package com.diplomski.dto;

import java.util.ArrayList;
import java.util.List;

public class WeekTrainingDTO {

    //jedan dan u nedelji - zbog datuma i dana u nedelji

    private List<TrainingDayDTO> allTrainings;
    private String date;
    private String day;

    public WeekTrainingDTO() {
        this.allTrainings = new ArrayList<>();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<TrainingDayDTO> getAllTrainings() {
        return allTrainings;
    }

    public void setAllTrainings(List<TrainingDayDTO> allTrainings) {
        this.allTrainings = allTrainings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
