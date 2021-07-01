package com.veer.studentdb.service;

import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.veer.studentdb.entity.User;
import com.veer.studentdb.jwtrequest.JwtRequest;
import com.veer.studentdb.jwtrequest.JwtResponse;
import com.veer.studentdb.repository.UserRepo;
import com.veer.studentdb.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	// returning generated token

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		log.info(this.getClass().getSimpleName() + " - getting user name (jwt service)");
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();

		User user = userRepo.findByUserName(userName);
		log.info(this.getClass().getSimpleName() + " - user");
		String newGeneratedToken = jwtUtil.generateToken(userName);
		return new JwtResponse(user, newGeneratedToken);

	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		log.info(this.getClass().getSimpleName() + " - able to find (jwt Service class");
		User user = userRepo.findByUserName(userName);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}

	}

	private Set getAuthority(User user) {

		log.info(this.getClass().getSimpleName() + " - autharity to role(jwt service ");
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

	private void authenticate(String userName, String userPassword) throws Exception {

		log.info(this.getClass().getSimpleName() + "-- credentail exceptions");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
