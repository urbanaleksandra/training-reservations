package com.diplomski.service;

import com.diplomski.dto.ReservedTrainingDTO;
import org.springframework.http.ResponseEntity;

public interface ReservedTrainingService {

    ResponseEntity<?> schedule(ReservedTrainingDTO reservedTrainingDTO);

    int getNumberOfScheduled(Long id);

}
