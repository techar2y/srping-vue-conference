package com.arty.simpleCRUD.controllers;

import com.arty.simpleCRUD.domains.Schedule;
import com.arty.simpleCRUD.repos.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules(@RequestParam(required = false) String name){
        try {
            List<Schedule> schedules = new ArrayList<Schedule>();

            if (name == null || name.length() == 0)
                scheduleRepository.findAll().forEach(schedules::add);
            else
                scheduleRepository.findByNameContaining(name).forEach(schedules::add);

            if (schedules.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/schedules")
    public ResponseEntity<Schedule> deleteAllSchedules(){
        try {
            scheduleRepository.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/schedules")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule){
        try {
            Schedule _schedule = scheduleRepository.save(new Schedule(
                    schedule.getDate(), schedule.getSubject(),
                    schedule.getDescription(), schedule.getRoom(), schedule.getName()
            ));
            return new ResponseEntity<>(_schedule, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable("id") Long id){
        try {
            Schedule schedule = scheduleRepository.getById(id);
            Optional<Schedule> scheduleData = scheduleRepository.findById(id);
            if (schedule != null) {
                return new ResponseEntity<>(schedule, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Schedule> deleteScheduleById(@PathVariable("id") Long id){
        try {
            scheduleRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable("id") Long id, @RequestBody Schedule schedule){
        try {
            Schedule newSchedule = scheduleRepository.getById(id);
            if(newSchedule == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


            if (schedule != null) {
                newSchedule.setDate(schedule.getDate());
                newSchedule.setDescription(schedule.getDescription());
                newSchedule.setRoom(schedule.getRoom());
                newSchedule.setSubject(schedule.getSubject());
                newSchedule = scheduleRepository.save(newSchedule);
            }

            return new ResponseEntity<>(newSchedule, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
