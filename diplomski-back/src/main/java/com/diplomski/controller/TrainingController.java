package com.diplomski.controller;

import com.diplomski.dto.NewTrainingDayDTO;
import com.diplomski.dto.TrainingDTO;
import com.diplomski.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping
    public TrainingDTO createTraining(@RequestBody NewTrainingDayDTO request){
        return trainingService.createNewTraining(request);
    }

    @PutMapping(value = "/{id}")
    public TrainingDTO updateTraining(@RequestBody NewTrainingDayDTO request, @PathVariable Long id){
        return trainingService.updateTraining(request, id);
    }
}