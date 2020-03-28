package com.caceis.monitor.task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.caceis.monitor.service.TaskService;

@Component
@ManagedResource(objectName = "MyApp:name=ScheduledTask")
@Configuration
public class ScheduledTask {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Value("${inputFolder.activated}")
	private boolean taskActivated;

	@Autowired
	private TaskService taskService;

	@PostConstruct
	public void afterPropertiesSet() {
		System.out.println("ScheduledTask.afterPropertiesSet");

		System.out.println("taskActivated:" + taskActivated);
	}

	@ManagedAttribute
	public boolean isTaskActivated() {
		return taskActivated;
	}

	@ManagedAttribute
	public void setTaskActivated(boolean newValue) {
		taskActivated = newValue;
	}

	@Scheduled(fixedDelayString = "${inputFolder.fixedDelay}")
	public void run() {
		if (!taskActivated) {
			return;
		}

		try {
			taskService.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Every 1 minute starting at 8:00 am and ending on 18:55 pm from Monday to
	 * Friday
	 */
	@Scheduled(cron = "${inputFolder.cronDelay}")
	public void runViaCron() {
		if (!taskActivated) {
			return;
		}
		System.out.println("The CRON time is now " + dateFormat.format(new Date()));
	}
}
