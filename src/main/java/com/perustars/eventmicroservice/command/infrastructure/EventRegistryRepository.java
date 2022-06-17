package com.perustars.eventmicroservice.command.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRegistryRepository extends JpaRepository<EventRegistry,String> {
    Optional<EventRegistry> getByEventId(String eventRegistryId);
}
