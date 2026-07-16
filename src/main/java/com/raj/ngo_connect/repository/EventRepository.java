package com.raj.ngo_connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.raj.ngo_connect.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
List<Event> findByEventNameContainingIgnoreCase(String keyword);
}