package com.era.api.dao;

import com.era.api.model.SessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogTableRepository extends JpaRepository<SessionLog, Integer> {
}
