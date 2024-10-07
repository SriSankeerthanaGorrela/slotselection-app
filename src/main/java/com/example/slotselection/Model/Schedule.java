package com.example.slotselection.Model;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for each schedule entry

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserTable user;  // The user who selected this schedule

    private LocalTime startTime;  // Start time of the available slot

    private LocalTime endTime;  // End time of the available slot

    private String weekDay;  // Day of the week (e.g., Monday, Tuesday)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserTable getUser() {
        return user;
    }

    public void setUser(UserTable user) {
        this.user = user;
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

    // Constructors, getters, and setters
}
