package com.caceis.monitor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.caceis.monitor.task.ScheduledTask;

import io.swagger.annotations.ApiOperation;

/**
 * RestController pour piloter l'activitée des tâches planifiées.
 *
 */
@RestController
@RequestMapping(value = "/scheduledTask")
public class TaskRestController {

	@Autowired
	private ScheduledTask scheduledTask;

	@ApiOperation(value = "Permet de démarrer les tâches planifiées")
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public @ResponseBody String startJmsListener() {

		scheduledTask.setTaskActivated(true);
		return "Scheduled task restarted";
	}

	@ApiOperation(value = "Permet d'arrêter les tâches planifiées")
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public @ResponseBody String stopJmsListener() {
		scheduledTask.setTaskActivated(false);
		return "Scheduled task Stopped";
	}

	@ApiOperation(value = "Permet de connaître l'état des tâches planifiées")
	@RequestMapping(value = "/state", method = RequestMethod.GET)
	public @ResponseBody String stateJmsListener() {
		return scheduledTask.isTaskActivated() ? "Scheduled task started" : "Scheduled task stopped";
	}
}
