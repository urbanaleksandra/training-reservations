package com.diplomski.service;

import com.diplomski.dto.TrainingDTO;
import com.diplomski.dto.TrainingDayDTO;
import com.diplomski.dto.WeekTrainingDTO;
import com.diplomski.model.TrainingDay;
import com.diplomski.repository.TrainingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingDayServiceImpl implements TrainingDayService{

    @Autowired
    private TrainingDayRepository trainingDayRepository;

    @Override
    public Optional<TrainingDay> findById(Long id) {
        return trainingDayRepository.findById(id);
    }

    @Override
    public List<TrainingDay> findAll() {
        return trainingDayRepository.findAll();
    }

    @Override
    public List<WeekTrainingDTO> getAll() {

        Format dateFormat = new SimpleDateFormat("EEE,MM-dd-yyyy");
        String res = dateFormat.format(new Date());

        Format dateFormat1 = new SimpleDateFormat("MM-dd-yyyy");
        String todayDate = dateFormat1.format(new Date(System.currentTimeMillis()));

        String plusOneDay = dateFormat1.format(new Date(System.currentTimeMillis() + 86400000));
        String plusTwoDays = dateFormat1.format(new Date(System.currentTimeMillis() + 86400000 * 2));
        String plusThreeDays = dateFormat1.format(new Date(System.currentTimeMillis() + 86400000 * 3));
        String plusFourDays = dateFormat1.format(new Date(System.currentTimeMillis() + 86400000 * 4));
        String plusFiveDays = dateFormat1.format(new Date(System.currentTimeMillis() + 86400000 * 5));
        String plusSixDays = dateFormat1.format(new Date(System.currentTimeMillis() + 86400000 * 6));

        System.out.println(todayDate + plusOneDay + plusTwoDays);

        List<WeekTrainingDTO> retVal = new ArrayList<>();

        WeekTrainingDTO listMon = new WeekTrainingDTO();
        WeekTrainingDTO listTue = new WeekTrainingDTO();
        WeekTrainingDTO listWed = new WeekTrainingDTO();
        WeekTrainingDTO listThu = new WeekTrainingDTO();
        WeekTrainingDTO listFri = new WeekTrainingDTO();
        WeekTrainingDTO listSat = new WeekTrainingDTO();

        for(TrainingDay trainingDay : trainingDayRepository.findAll()){
            switch (trainingDay.getDay().getDay()){
                case "MONDAY" :
                    System.out.println("usao u mon");
                    listMon.getAllTrainings().add(mapToDTO(trainingDay));
                    break;
                case "TUESDAY" :
                    listTue.getAllTrainings().add(mapToDTO(trainingDay));
                    break;
                case "WEDNESDAY" :
                    listWed.getAllTrainings().add(mapToDTO(trainingDay));
                    break;
                case "THURSDAY" :
                    listThu.getAllTrainings().add(mapToDTO(trainingDay));
                    break;
                case  "FRIDAY" :
                    listFri.getAllTrainings().add(mapToDTO(trainingDay));
                    break;
                case  "SATURDAY" :
                    listSat.getAllTrainings().add(mapToDTO(trainingDay));
                    break;
            }
        }

        String today = res.split(",")[0];
        System.out.println(today);
        if(today.equals("pon")){
            listMon.setDate(todayDate);
            retVal.add(listMon);
            listTue.setDate(plusOneDay);
            retVal.add(listTue);
            listWed.setDate(plusTwoDays);
            retVal.add(listWed);
            listThu.setDate(plusThreeDays);
            retVal.add(listThu);
            listFri.setDate(plusFourDays);
            retVal.add(listFri);
            listSat.setDate(plusFiveDays);
            retVal.add(listSat);
        }else if(today.equals("uto")){
            listTue.setDate(todayDate);
            retVal.add(listTue);
            listWed.setDate(plusOneDay);
            retVal.add(listWed);
            listThu.setDate(plusTwoDays);
            retVal.add(listThu);
            listFri.setDate(plusThreeDays);
            retVal.add(listFri);
            listSat.setDate(plusFourDays);
            retVal.add(listSat);
            listMon.setDate(plusSixDays);
            retVal.add(listMon);
        }else if(today.equals("sre")){
            listWed.setDate(todayDate);
            retVal.add(listWed);
            listThu.setDate(plusOneDay);
            retVal.add(listThu);
            listFri.setDate(plusTwoDays);
            retVal.add(listFri);
            listSat.setDate(plusThreeDays);
            retVal.add(listSat);
            listMon.setDate(plusFiveDays);
            retVal.add(listMon);
            listTue.setDate(plusSixDays);
            retVal.add(listTue);
        }else if(today.equals("čet")){
            listThu.setDate(todayDate);
            retVal.add(listThu);
            listFri.setDate(plusOneDay);
            retVal.add(listFri);
            listSat.setDate(plusTwoDays);
            retVal.add(listSat);
            listMon.setDate(plusFourDays);
            retVal.add(listMon);
            listTue.setDate(plusFiveDays);
            retVal.add(listTue);
            listWed.setDate(plusSixDays);
            retVal.add(listWed);
        }else if(today.equals("pet")){
            listFri.setDate(todayDate);
            retVal.add(listFri);
            listSat.setDate(plusOneDay);
            retVal.add(listSat);
            listMon.setDate(plusThreeDays);
            retVal.add(listMon);
            listTue.setDate(plusFourDays);
            retVal.add(listTue);
            listWed.setDate(plusFiveDays);
            retVal.add(listWed);
            listThu.setDate(plusSixDays);
            retVal.add(listThu);
        }else if(today.equals("sub")){
            listSat.setDate(todayDate);
            retVal.add(listSat);
            listMon.setDate(plusTwoDays);
            retVal.add(listMon);
            listTue.setDate(plusThreeDays);
            retVal.add(listTue);
            listWed.setDate(plusFourDays);
            retVal.add(listWed);
            listThu.setDate(plusFiveDays);
            retVal.add(listThu);
            listFri.setDate(plusSixDays);
            retVal.add(listFri);
        }else if(today.equals("ned")){
            listMon.setDate(plusOneDay);
            retVal.add(listMon);
            listTue.setDate(plusTwoDays);
            retVal.add(listTue);
            listWed.setDate(plusThreeDays);
            retVal.add(listWed);
            listThu.setDate(plusFourDays);
            retVal.add(listThu);
            listFri.setDate(plusFiveDays);
            retVal.add(listFri);
            listSat.setDate(plusSixDays);
            retVal.add(listSat);
        }

        return retVal;
    }

    public TrainingDayDTO mapToDTO(TrainingDay trainingDay){

        TrainingDayDTO dto = new TrainingDayDTO();
        dto.setId(trainingDay.getId());
        dto.setDay(trainingDay.getDay().getDay());
        dto.setStartsAt(trainingDay.getStartsAt().toString());
        dto.setTrainer(trainingDay.getTrainer());

        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setId(trainingDay.getTraining().getId());
        trainingDTO.setCapacity(trainingDay.getTraining().getCapacity());
        trainingDTO.setDescription(trainingDay.getTraining().getDescription());
        trainingDTO.setDuration(trainingDay.getTraining().getDuration());
        trainingDTO.setName(trainingDay.getTraining().getName());

        dto.setTraining(trainingDTO);

        return dto;
    }
}
