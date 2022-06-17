package com.perustars.eventmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventViewRepository extends JpaRepository<EventView,String> {
}
