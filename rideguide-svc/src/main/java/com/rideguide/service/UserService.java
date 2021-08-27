package com.rideguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rideguide.entity.DriverInformation;
import com.rideguide.entity.User;
import com.rideguide.entity.UserPasswordResetRequest;
import com.rideguide.repository.DriverInformationRepository;
import com.rideguide.repository.UserPasswordResetRequestRepository;
import com.rideguide.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    private UserPasswordResetRequestRepository userPasswordResetRequestRepository;

    

    private BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserPasswordResetRequestRepository userPasswordResetRequestRepository,
                       BCryptPasswordEncoder bCryptEncoder) {
        this.userRepository = userRepository;
        this.userPasswordResetRequestRepository = userPasswordResetRequestRepository;
        this.bCryptEncoder = bCryptEncoder;
       
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers() {
		
		return userRepository.findAll();
	}
    

    public void updatePassword(String token, String newPassword) {
        UserPasswordResetRequest userPasswordResetRequest = userPasswordResetRequestRepository.findByExternalId(token);
        User user = userRepository.getById(userPasswordResetRequest.getUserId());
        user.setPassword(bCryptEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public UserPasswordResetRequest createPasswordResetRequest(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        UserPasswordResetRequest userPasswordResetRequest = new UserPasswordResetRequest();
        userPasswordResetRequest.setUserId(user.getId());
        userPasswordResetRequest.setExpireDateTime(Timestamp.valueOf(LocalDateTime.now().plusHours(2)));
        userPasswordResetRequest.setExternalId(UUID.randomUUID().toString());
        userPasswordResetRequestRepository.save(userPasswordResetRequest);

        return userPasswordResetRequest;

    }

    public UserPasswordResetRequest getPasswordResetRequest(String extId) {
        return userPasswordResetRequestRepository.findByExternalId(extId);
    }
    
    @Transactional
	public User update(String id, User user) {

    	user.setId(id);
		return userRepository.save(user);
	}

	@Transactional
	public User save(User user) {

		
		return userRepository.save(user);

	}

	@Transactional
	public void delete(final String id) {
		userRepository.deleteById(id);
	}

   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}