package com.caceis.monitor.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caceis.monitor.entity.ScheduledTask;
import com.caceis.monitor.repository.ScheduledTaskDao;

// FIXME n'est plus utile, remplacer par le SchedulerService
/**
 * 
 * @author avebertrand
 * @deprecated
 */
@Component
public class TaskInitializationService {

	@Autowired
	private ScheduledTaskDao scheduledTaskDao;

	@Autowired
	private MailClient mailClient;

	/**
	 * Charge la configuration en DB, et active les bonnes tasks de façon répétitive
	 */
	@PostConstruct
	public void afterPropertiesSet() {
		System.out.println("TaskInitializationService.afterPropertiesSet");

		List<ScheduledTask> list = scheduledTaskDao.findAll();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}

		// mailClient.prepareAndSend("toto@smtp.com", "test");
	}
}
