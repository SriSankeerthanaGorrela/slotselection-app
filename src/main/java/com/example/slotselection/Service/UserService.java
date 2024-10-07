package com.example.slotselection.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.slotselection.Model.Schedule;
import com.example.slotselection.Model.UserTable;
import com.example.slotselection.Repository.ScheduleRepository;
import com.example.slotselection.Repository.UserRepository;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    // // Create a new user and set default role to "USER"
    // public void createUser(UserTable user) {
    //     // user.setRole("USER");  // Set default role as "USER"
    //     userRepository.save(user);
    // }

    // public void createUser(UserTable user) {
    // user.setRole("USER");
    // try {
    //     userRepository.save(user);
    // } catch (DataIntegrityViolationException e) {
    //     // Handle the exception, e.g., throw a custom exception or return an error response
    //     throw new RuntimeException("Email already exists.");
    // }
    public void createUser(UserTable user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");  // Default role
        }
        userRepository.save(user);
    }


    // Add a new schedule for the user
    public void addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    // Fetch all schedules for a specific user
    public List<Schedule> getUserSchedules(Long userId) {
        return scheduleRepository.findByUserId(userId);  // Corrected method to return a list
    }
    public Optional<UserTable> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
