package tech.pswaldia.springblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.pswaldia.springblog.daos.UserRepository;
import tech.pswaldia.springblog.daos.ValidateAlreadyPresentUser;
import tech.pswaldia.springblog.dtos.LoginRequest;
import tech.pswaldia.springblog.dtos.RegisterRequest;
import tech.pswaldia.springblog.entites.User;
import tech.pswaldia.springblog.security.JWTProvider;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ValidateAlreadyPresentUser validateAlreadyPresentUser;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTProvider jwtProvider;
	
	public boolean signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		if (validateAlreadyPresentUser.findByUserNameOrEmail(user.getUserName(), user.getEmail()) == null) {
			userRepository.save(user);
			return true;
		} else
			return false;
	}

	public String login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtProvider.generateToken(authentication);
	}
}
