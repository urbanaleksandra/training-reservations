package com.diplomski.controller;

import com.diplomski.dto.ReservedTrainingDTO;
import com.diplomski.model.ReservedTraining;
import com.diplomski.service.ReservedTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/reserved-training")
public class ReservedTrainingController {

    @Autowired
    private ReservedTrainingService reservedTrainingService;

    @PostMapping(value = "")
    public ResponseEntity<?> schedule(@RequestBody ReservedTrainingDTO reservedTrainingDTO){
        return reservedTrainingService.schedule(reservedTrainingDTO);
    }

    @GetMapping(value = "/{id}")
    public int getNumberOfScheduled(@PathVariable Long id) {
        return reservedTrainingService.getNumberOfScheduled(id);
    }
}
