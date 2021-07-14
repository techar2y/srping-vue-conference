package com.arty.simpleCRUD.repos;

import com.arty.simpleCRUD.domains.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>
{
    List<Schedule> findByNameContaining(String name);

    Schedule getById(Long id);

}
