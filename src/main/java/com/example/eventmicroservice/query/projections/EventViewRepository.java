package com.example.eventmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventViewRepository extends JpaRepository<EventView,String> {
}
