package com.perustars.eventmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventHistoryViewRepository extends JpaRepository<EventHistoryView,String> {
    @Query(value = "SELECT * FROM event_history_view WHERE event_history_id =(SELECT MAX(event_history_id) FROM event_history_view WHERE event_id = :eventId)",nativeQuery = true)
    Optional<EventHistoryView> getLastByEventId(String eventId);

    @Query(value = "SELECT * FROM event_history_view WHERE event_id = :eventId ORDER BY created_at",nativeQuery = true)
    List<EventHistoryView> getHistoryByEventId(String eventId);
}
