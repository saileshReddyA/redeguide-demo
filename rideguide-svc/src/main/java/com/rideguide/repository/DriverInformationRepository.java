package com.rideguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rideguide.entity.DriverInformation;

import java.util.List;
import java.util.Set;

@Repository
public interface DriverInformationRepository extends JpaRepository<DriverInformation, String> {
    //Set<DriverInformation> findDriverInformationByOrgIdIn(List<Long> orgIdList);
//	Set<DriverInformation> findByUserId(Long userId);

	Set<DriverInformation> findByUserId(String id);

	void deleteById(Long id);
}
