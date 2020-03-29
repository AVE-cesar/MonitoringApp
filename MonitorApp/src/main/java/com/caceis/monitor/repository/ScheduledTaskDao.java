package com.caceis.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caceis.monitor.entity.ScheduledTask;

@Repository
public interface ScheduledTaskDao extends JpaRepository<ScheduledTask, Long> {

}
