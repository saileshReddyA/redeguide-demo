package com.rideguide.controller;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.rideguide.entity.User;
import com.rideguide.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Api(tags = "User Mgmt")
@RestController
@Validated
@RequestMapping("/v1/user")
@CrossOrigin
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

  

    @GetMapping("/current")
    public ResponseEntity<User> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.getUserByEmail(authentication.getName()));
    }
    
    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> getUserByEmail(@NotBlank @PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user == null)
            throw new ResourceNotFoundException();
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") String id) {
        Optional<User> user = userService.getUserById(id);
        if (user == null)
            throw new ResourceNotFoundException();
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/list")
	public ResponseEntity<List<User>> getList() {

		return ResponseEntity.ok(userService.getUsers());
	}
    
    @PostMapping
	public User create(@Validated @RequestBody User newUser) {
		LOGGER.info("Create newUser .");
		return this.userService.save(newUser);
	}
    

	@PutMapping("/{id}")
	public User update(@PathVariable("id") String id,
			@Valid @RequestBody User updatedUser) {
		LOGGER.info("Update User .");
		return this.userService.update(id, updatedUser);
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable String id) {
		LOGGER.info("Delete User .");
		userService.delete(id);
		return id;

	}
   


}