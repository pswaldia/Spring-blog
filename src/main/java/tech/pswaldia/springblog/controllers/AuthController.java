package tech.pswaldia.springblog.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.pswaldia.springblog.dtos.LoginRequest;
import tech.pswaldia.springblog.dtos.RegisterRequest;
import tech.pswaldia.springblog.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		if(authService.signup(registerRequest)) {
			logger.info("User added to database!");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			logger.error("User already exists");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
}
