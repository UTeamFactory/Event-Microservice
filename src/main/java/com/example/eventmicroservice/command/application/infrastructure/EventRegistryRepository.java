package com.example.eventmicroservice.command.application.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRegistryRepository extends JpaRepository<EventRegistry,String> {
    Optional<EventRegistry> getByEventId(String eventRegistryId);
}
