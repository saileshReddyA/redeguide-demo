package com.rideguide.service;

import java.util.Set;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rideguide.converter.DriverInformationMapper;
import com.rideguide.entity.DriverInformation;
import com.rideguide.entity.User;
import com.rideguide.exception.NoSuchEntityException;
import com.rideguide.repository.DriverInformationRepository;
import com.rideguide.repository.UserPasswordResetRequestRepository;
import com.rideguide.repository.UserRepository;
import com.rideguide.request.dto.DriverInformationDto;


@Service
@Transactional(readOnly = true)
public class DriverInformationService {

	//private static final DriverInformationMapper MAPPER = Mappers.getMapper(DriverInformationMapper.class);

	
	private UserRepository userRepository;

	private DriverInformationRepository driverInformationRepository;
	
	 @Autowired
	 public DriverInformationService(UserRepository userRepository,
			 DriverInformationRepository driverInformationRepository) {
	        this.driverInformationRepository = driverInformationRepository;
	        this.userRepository=userRepository;
	      
	       
	    }

	public Set<DriverInformation> getDriverInformation(String userId) {
		User user = userRepository.findByEmail(userId);
		// List<Long> orgIdList = user.getOrganizations().stream().map(o ->
		// o.getId()).collect(Collectors.toList());
		return driverInformationRepository.findByUserId(user.getId());
	}

	@Transactional
	public DriverInformation update(Long id, DriverInformation driverInformation) {

		
		return driverInformationRepository.save(driverInformation);
	}

	@Transactional
	public DriverInformation save(DriverInformation driverInformation) {

		
		return driverInformationRepository.save(driverInformation);

	}

	@Transactional
	public void delete(final Long id) {
		driverInformationRepository.deleteById(id);
	}

	

}