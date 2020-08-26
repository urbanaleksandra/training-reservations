package com.diplomski.controller;

import com.diplomski.dto.ReservedTrainingDTO;
import com.diplomski.dto.TrainingDayDTO;
import com.diplomski.dto.WeekTrainingDTO;
import com.diplomski.model.ReservedTraining;
import com.diplomski.model.User;
import com.diplomski.service.TrainingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/training-day")
public class TrainingDayController {

    @Autowired
    private TrainingDayService trainingDayService;

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('user')")
    public List<WeekTrainingDTO> getAll() {
        return trainingDayService.getAll();
    }

    @GetMapping(value = "/training/{id}")
    @PreAuthorize("hasAuthority('user')")
    public List<TrainingDayDTO> getAllByTraining(@PathVariable Long id) {
        return this.trainingDayService.findAllByTraining(id);
    }

}
