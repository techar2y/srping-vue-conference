package com.arty.simpleCRUD.controllers;

import com.arty.simpleCRUD.domains.Room;
import com.arty.simpleCRUD.repos.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RoomController
{
    @Autowired
    private IRoomRepository roomRepository;

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        try {
            List<Room> rooms = roomRepository.findAll();

            if(rooms.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
