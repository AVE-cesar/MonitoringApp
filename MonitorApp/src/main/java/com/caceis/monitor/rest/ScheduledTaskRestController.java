package com.caceis.monitor.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caceis.monitor.entity.ScheduledTask;
import com.caceis.monitor.repository.ScheduledTaskDao;

@RestController
public class ScheduledTaskRestController {

	@Autowired
	private ScheduledTaskDao personDao;

	// Récupérer la liste des persons
	@RequestMapping(value = "/scheduledtask", method = RequestMethod.GET)
	public ResponseEntity<List<ScheduledTask>> getAllPersons() {
		List<ScheduledTask> list = personDao.findAll();
		return new ResponseEntity<List<ScheduledTask>>(list, HttpStatus.OK);
	}

	/**
	 * Create a new Person.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScheduledTask> create(@RequestBody ScheduledTask person) throws URISyntaxException {

		ScheduledTask result = personDao.save(person);

		return ResponseEntity.created(new URI("/scheduledtask/" + result.getId())).body(result);
	}
}
