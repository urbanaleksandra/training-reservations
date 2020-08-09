package com.diplomski.dto;

public class TrainingDTO {

    private Long id;

    private String name;

    private String description;

    private int capacity;

    private Long duration;

    public TrainingDTO(Long id, String name, String description, int capacity, Long duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.duration = duration;
    }

    public TrainingDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
