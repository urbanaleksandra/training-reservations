package com.diplomski.repository;

import com.diplomski.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {

    Day findByDay(String name);
}
