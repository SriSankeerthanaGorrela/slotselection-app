package com.example.slotselection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.slotselection.Model.MeetingRequestDTO;
import com.example.slotselection.Model.TimeSlot;
import com.example.slotselection.Model.UserTable;
import com.example.slotselection.Service.AdminService;
import com.example.slotselection.Service.UserService;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService; // For admin registration

    // Existing functionality to get common slots for the admin
    @GetMapping("/slots/{weekDay}")
    public ResponseEntity<List<TimeSlot>> getCommonSlots(@PathVariable String weekDay) {
        List<TimeSlot> commonSlots = adminService.getCommonSlots(weekDay);
        return ResponseEntity.ok(commonSlots);
    }

    // Admin registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody UserTable admin) {
        // Ensure the admin has a role of 'ADMIN'
        admin.setRole("ADMIN");
        userService.createUser(admin); // Store the admin in the user table

        return ResponseEntity.ok("Admin successfully registered");
    }

    // Endpoint to schedule a meeting and store the admin data in the Meeting table
    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleMeeting(@RequestBody MeetingRequestDTO meetingRequestDTO) {
        adminService.scheduleMeeting(meetingRequestDTO);
        return ResponseEntity.ok("Meeting successfully scheduled and admin data stored");
    }
}