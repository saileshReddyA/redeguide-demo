package com.rideguide.controller;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rideguide.entity.DriverInformation;
import com.rideguide.request.dto.DriverInformationDto;
import com.rideguide.service.DriverInformationService;
import java.util.Set;

import javax.validation.Valid;

@Api(tags = "DriverInformation")
@RestController
@Validated
@RequestMapping("/v1/driverinformation")
@CrossOrigin
public class DriverInformationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverInformationController.class);

	private DriverInformationService driverInformationService;

	@Autowired
	public DriverInformationController(DriverInformationService driverInformationService) {
		this.driverInformationService = driverInformationService;
	}

	@GetMapping("/list")
	public ResponseEntity<Set<DriverInformation>> getList() {

		LOGGER.info("Get DriverInformation .");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return ResponseEntity.ok(driverInformationService.getDriverInformation(authentication.getName()));
	}

	@PostMapping
	public DriverInformation create(@Validated @RequestBody DriverInformation newDriverInformation) {

		LOGGER.info("Create DriverInformation .");
		return this.driverInformationService.save(newDriverInformation);
	}
	
	@PutMapping("/{id}")
	public DriverInformation update(@PathVariable("id") Long id,
			@Valid @RequestBody DriverInformation updatedDriverInformation) {
		LOGGER.info("Update DriverInformation .");
		return this.driverInformationService.update(id, updatedDriverInformation);
	}

	@DeleteMapping("{id}")
	public Long delete(@PathVariable Long id) {
		LOGGER.info("Delete DriverInformation .");
		driverInformationService.delete(id);
		return id;

	}
}
