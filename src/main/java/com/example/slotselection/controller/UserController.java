package com.example.slotselection.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.slotselection.Model.Schedule;
import com.example.slotselection.Model.UserTable;
import com.example.slotselection.Service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserTable user) {
        userService.createUser(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/{userId}/schedule")
    public ResponseEntity<?> addSchedule(@PathVariable Long userId, @RequestBody Schedule schedule) {
        Optional<UserTable> userOptional = userService.getUserById(userId);

    // Handle case if user is not found
    if (!userOptional.isPresent()) {
        return ResponseEntity.badRequest().body("User not found");
    }

    // Set the user in the schedule
    schedule.setUser(userOptional.get());  // Extract the UserTable from Optional
    userService.addSchedule(schedule);
    return ResponseEntity.ok("Schedule added");
}



    @GetMapping("/{userId}/schedules")
    public ResponseEntity<List<Schedule>> getUserSchedules(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserSchedules(userId));
    }
}