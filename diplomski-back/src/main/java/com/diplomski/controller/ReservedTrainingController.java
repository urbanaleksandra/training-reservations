package com.diplomski.controller;

import com.diplomski.dto.ReservedTrainingDTO;
import com.diplomski.model.ReservedTraining;
import com.diplomski.service.ReservedTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/reserved-training")
public class ReservedTrainingController {

    @Autowired
    private ReservedTrainingService reservedTrainingService;

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('simple')")
    public ResponseEntity<?> schedule(@RequestBody ReservedTrainingDTO reservedTrainingDTO){
        try{
            return reservedTrainingService.schedule(reservedTrainingDTO);
        }catch (Exception e){
            return new ResponseEntity<>(reservedTrainingDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}/{date}")
    @PreAuthorize("hasAuthority('user')")
    public int getNumberOfScheduled(@PathVariable("id") Long id, @PathVariable("date") String date) {
        return reservedTrainingService.getNumberOfScheduled(id, date);
    }

    @PostMapping(value = "confirm-arrival")
    @PreAuthorize("hasAuthority('employee')")
    public ResponseEntity<?> confirmArrival(@RequestBody ReservedTrainingDTO reservedTrainingDTO){
        return reservedTrainingService.confirmArrival(reservedTrainingDTO);
    }
}
