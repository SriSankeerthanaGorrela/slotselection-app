package com.example.slotselection.Service;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slotselection.Model.Meeting;
import com.example.slotselection.Model.MeetingRequestDTO;
import com.example.slotselection.Model.Schedule;
import com.example.slotselection.Model.TimeSlot;
import com.example.slotselection.Model.UserTable;
import com.example.slotselection.Repository.MeetingRepository;
import com.example.slotselection.Repository.ScheduleRepository;
import com.example.slotselection.Repository.UserRepository;


@Service
public class AdminService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    public List<TimeSlot> getCommonSlots(String weekDay) {
        // Get all schedules for the given weekday
         List<Schedule> schedules = scheduleRepository.findByWeekDay(weekDay);

        // Sort schedules by start time to make overlapping calculation easier
        schedules.sort(Comparator.comparing(Schedule::getStartTime));

        // This will store the resulting common time slots
        List<TimeSlot> commonSlots = new ArrayList<>();

        if (schedules.isEmpty()) {
            return commonSlots; // No schedules available
        }

        // Find common overlapping slots
        LocalTime start = schedules.get(0).getStartTime();
        LocalTime end = schedules.get(0).getEndTime();

        for (int i = 1; i < schedules.size(); i++) {
            Schedule current = schedules.get(i);

            // Check if there is an overlap
            if (current.getStartTime().isBefore(end)) {
                // There is an overlap, so we take the maximum of the start times and the minimum of the end times
                start = current.getStartTime().isAfter(start) ? current.getStartTime() : start;
                end = current.getEndTime().isBefore(end) ? current.getEndTime() : end;
            } else {
                // If no overlap, save the previous common slot and reset
                if (!start.equals(end)) {
                    commonSlots.add(new TimeSlot(start, end));
                }
                start = current.getStartTime();
                end = current.getEndTime();
            }
        }

        // Add the final common slot
        if (!start.equals(end)) {
            commonSlots.add(new TimeSlot(start, end));
        }

        return commonSlots;
    }

    public void scheduleMeeting(MeetingRequestDTO meetingRequestDTO) {
        Optional<UserTable> adminOptional = userRepository.findById(meetingRequestDTO.getAdminId());
        if (!adminOptional.isPresent() || !adminOptional.get().getRole().equals("ADMIN")) {
            throw new RuntimeException("Admin not found or not an admin");
        }
        UserTable admin = adminOptional.get();

        
        // Find the user by ID
        Optional<UserTable> userOptional = userRepository.findById(meetingRequestDTO.getUserId());

        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        UserTable user = userOptional.get();

        // Create a new meeting entity
        Meeting meeting = new Meeting();
        meeting.setAdmin(admin);
        meeting.setUser(user);
        meeting.setStartTime(meetingRequestDTO.getStartTime());
        meeting.setEndTime(meetingRequestDTO.getEndTime());
        meeting.setWeekDay(meetingRequestDTO.getWeekDay());
        meeting.setScheduledTime(LocalTime.now());  // Set current timestamp when the meeting is scheduled

        // Save the meeting to the database
        meetingRepository.save(meeting);
    }
}
