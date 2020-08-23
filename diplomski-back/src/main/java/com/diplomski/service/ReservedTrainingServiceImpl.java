package com.diplomski.service;

import com.diplomski.dto.ReservedTrainingDTO;
import com.diplomski.model.*;
import com.diplomski.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Transactional(rollbackFor = {RuntimeException.class},readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<?> schedule(ReservedTrainingDTO reservedTrainingDTO) {

        Optional<TrainingDay> trainingDay = trainingDayRepository.findById(reservedTrainingDTO.getTrainingDay().getId());
        int numberOfScheduled = getNumberOfScheduled(reservedTrainingDTO.getTrainingDay().getId(), reservedTrainingDTO.getDate());
        if(trainingDay.get().getTraining().getCapacity() - numberOfScheduled > 0){
            System.out.println("usao u schedule" + reservedTrainingDTO);
            ReservedTraining reservedTraining = new ReservedTraining();
            reservedTraining.setDeleted(false);
            reservedTraining.setAttended(false);
            reservedTraining.setDate(reservedTrainingDTO.getDate());

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

        return new ResponseEntity<String>("Training capacity full.", HttpStatus.NOT_ACCEPTABLE);

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
    public int getNumberOfScheduled(Long id, String date) {

        Optional<TrainingDay> trainingDay = trainingDayRepository.findById(id);
        List<ReservedTraining> reservedTrainings = reservedTrainingRepository.findByTrainingDay(trainingDay.get());

        List<ReservedTraining> retVal = new ArrayList<>();

        if(reservedTrainings.size() > 0){
            for(ReservedTraining rt : reservedTrainings){
                if(rt.getDate().equals(date)){
                    retVal.add(rt);
                }
            }
        }

        return retVal.size();
    }

    @Scheduled(cron = "0 58 23 * * *")
    public void checkViolations(){

        System.out.println("usaooooo");

        List<ReservedTraining> reservedTrainings = reservedTrainingRepository.findAll();
        Format dateFormat1 = new SimpleDateFormat("MM-dd-yyyy");
        String todayDate = dateFormat1.format(new Date(System.currentTimeMillis()));

        for(ReservedTraining rt : reservedTrainings){
            if(rt.getDate().equals(todayDate) && !rt.isAttended()){
                Optional<SimpleUser> simpleUser = simpleUserRepository.findById(rt.getSimpleUser().getId());
                simpleUser.get().setViolations(simpleUser.get().getViolations()+1);
                simpleUserRepository.save(simpleUser.get());

                if(simpleUser.get().getViolations() >= 5){
                    simpleUser.get().getUser().setEnabled(false);
                    userRepository.save(simpleUser.get().getUser());
                }
            }
        }
        System.out.println("usao u ispis u 23:58");
    }
}
