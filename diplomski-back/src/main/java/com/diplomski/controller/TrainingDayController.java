package com.diplomski.controller;

import com.diplomski.dto.WeekTrainingDTO;
import com.diplomski.model.User;
import com.diplomski.service.TrainingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/training-day")
public class TrainingDayController {

    @Autowired
    private TrainingDayService trainingDayService;

    @GetMapping(value = "")
    public List<WeekTrainingDTO> getAll() {
        return trainingDayService.getAll();
    }

}
