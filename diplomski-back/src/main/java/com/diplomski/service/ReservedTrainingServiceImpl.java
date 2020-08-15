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
    public ResponseEntity<?> confirmArrival(ReservedTrainingDTO reservedTrainingDTO) {

        System.out.println("usao u confirmArrival" + reservedTrainingDTO);

        User user = userRepository.findByUsername(reservedTrainingDTO.getSimpleUser());
        SimpleUser simpleUser = simpleUserRepository.findByUser(user);
        List<ReservedTraining> alreadyReserved = reservedTrainingRepository.findBySimpleUser(simpleUser);

        System.out.println(alreadyReserved);
        if(alreadyReserved.size() > 0){
            for(ReservedTraining rt : alreadyReserved){
                if(rt.getTrainingDay().getId().equals(reservedTrainingDTO.getTrainingDay().getId()) && rt.getDate().equals(reservedTrainingDTO.getDate())){
                    if(rt.isAttended() == false){
                        rt.setAttended(true);
                        reservedTrainingRepository.save(rt);
                        return new ResponseEntity<String>("OK", HttpStatus.OK);
                    }else{
                        return new ResponseEntity<String>("Already arrived.", HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }

        return new ResponseEntity<String>("No schedule training for this user.", HttpStatus.BAD_REQUEST);

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
