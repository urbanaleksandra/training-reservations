package com.diplomski.service;

import com.diplomski.dto.ReservedTrainingDTO;
import com.diplomski.model.*;
import com.diplomski.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservedTrainingServiceImpl implements ReservedTrainingService {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private TrainingDayRepository trainingDayRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpleUserRepository simpleUserRepository;

    @Autowired
    private ReservedTrainingRepository reservedTrainingRepository;

    @Override
    public ResponseEntity<?> schedule(ReservedTrainingDTO reservedTrainingDTO) {

        System.out.println("usao u schedule" + reservedTrainingDTO);
        ReservedTraining reservedTraining = new ReservedTraining();
        reservedTraining.setDeleted(false);
        reservedTraining.setAttended(false);
        reservedTraining.setDate(reservedTrainingDTO.getDate());

        Optional<TrainingDay> trainingDay = trainingDayRepository.findById(reservedTrainingDTO.getTrainingDay().getId());
        reservedTraining.setTrainingDay(trainingDay.get());

        User user = userRepository.findByUsername(reservedTrainingDTO.getSimpleUser());
        SimpleUser simpleUser = simpleUserRepository.findByUser(user);
        reservedTraining.setSimpleUser(simpleUser);

        List<ReservedTraining> alreadyReserved = reservedTrainingRepository.findBySimpleUser(simpleUser);

        if(alreadyReserved != null){
            for(ReservedTraining rt : alreadyReserved){
                if(rt.getTrainingDay().getId().equals(trainingDay.get().getId()) && rt.getDate().equals(reservedTrainingDTO.getDate())){
                    return new ResponseEntity<String>("Already schedule.", HttpStatus.BAD_REQUEST);
                }
            }
        }

        reservedTrainingRepository.save(reservedTraining);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @Override
    public int getNumberOfScheduled(Long id) {

        Optional<TrainingDay> trainingDay = trainingDayRepository.findById(id);
        if(trainingDay == null){
            return 0;
        }
        List<ReservedTraining> reservedTrainings = reservedTrainingRepository.findByTrainingDay(trainingDay.get());

        return reservedTrainings.size();
    }
}
