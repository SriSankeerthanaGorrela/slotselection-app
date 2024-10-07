package com.example.slotselection.Model;

import java.time.LocalTime;

public class MeetingRequestDTO {
    private Long userId;  // ID of the user for whom the meeting is being scheduled
    private Long adminId;
    private LocalTime startTime;  // Start time of the meeting
    private LocalTime endTime;    // End time of the meeting
    private String weekDay;       // Weekday on which the meeting is scheduled

    // Getters and Setters
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}
