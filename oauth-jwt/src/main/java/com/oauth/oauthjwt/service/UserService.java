package com.oauth.oauthjwt.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.oauth.oauthjwt.entity.MasterUser;
import com.oauth.oauthjwt.entity.Role;
import com.oauth.oauthjwt.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MasterUser user = userRepository.findByUsername(username);
		if (user == null) {
			log.error("Invalid username or password");
			throw new UsernameNotFoundException("Bad credential");
		}
		
		Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

	 private Set<GrantedAuthority> getAuthorities(MasterUser user) {
        Set<Role> roleByUserId = user.getRoles();
        final Set<GrantedAuthority> authorities = roleByUserId.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase())).collect(Collectors.toSet());
        return authorities;
    }
}
