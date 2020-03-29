package com.caceis.monitor.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.caceis.monitor.entity.ScheduledTask;
import com.caceis.monitor.repository.ScheduledTaskDao;

@Service
public class SchedulerService implements SchedulingConfigurer {

	private ScheduledTaskRegistrar scheduledTaskRegistrar;

	private ScheduledFuture future;

	/**
	 * Pour charger les infos de la DB.
	 */
	@Autowired
	private ScheduledTaskDao scheduledTaskDao;

	@Bean
	public TaskScheduler poolScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		scheduler.setPoolSize(10);
		scheduler.initialize();
		return scheduler;
	}

	// We can have multiple tasks inside the same registrar as we can see below.
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		if (scheduledTaskRegistrar == null) {
			scheduledTaskRegistrar = taskRegistrar;
		}
		if (taskRegistrar.getScheduler() == null) {
			taskRegistrar.setScheduler(poolScheduler());
		}

		// on charge les tâches de la DB
		List<ScheduledTask> scheduledTasks = scheduledTaskDao.findAll();

		System.out.println("On ajoute " + scheduledTasks.size() + " tâches planifiées.");

		future = taskRegistrar.getScheduler().schedule(() -> scheduleFixed(), t -> {
			Calendar nextExecutionTime = new GregorianCalendar();
			Date lastActualExecutionTime = t.lastActualExecutionTime();
			nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
			nextExecutionTime.add(Calendar.SECOND, 7);
			return nextExecutionTime.getTime();
		});

		// or cron way
		CronTrigger croneTrigger = new CronTrigger("0/10 * * * * ?", TimeZone.getDefault());
		future = taskRegistrar.getScheduler().schedule(() -> scheduleCron("0/10 * * * * ?"), croneTrigger);

	}

	public void scheduleFixed() {
		System.out.println("scheduleFixed: Next execution time of this will always be 5 seconds");
	}

	public void scheduleCron(String cron) {
		System.out.println("scheduleCron: Next execution time of this taken from cron expression -> " + cron);
	}

	/**
	 * @param mayInterruptIfRunning {@code true} if the thread executing this task
	 *                              should be interrupted; otherwise, in-progress
	 *                              tasks are allowed to complete
	 */
	public void cancelTasks(boolean mayInterruptIfRunning) {
		System.out.println("Cancelling all tasks");
		future.cancel(mayInterruptIfRunning); // set to false if you want the running task to be completed first.
	}

	public void activateScheduler() {
		System.out.println("Re-Activating Scheduler");
		configureTasks(scheduledTaskRegistrar);
	}

}
