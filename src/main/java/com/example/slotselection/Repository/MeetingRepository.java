package com.example.slotselection.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slotselection.Model.Meeting;


@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
