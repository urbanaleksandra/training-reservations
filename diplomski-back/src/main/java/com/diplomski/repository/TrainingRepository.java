package com.diplomski.repository;

import com.diplomski.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TrainingRepository extends JpaRepository<Training, Long> {

    Optional<Training> findById(Long id);

    List<Training> findAll();
}