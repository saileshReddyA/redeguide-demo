package com.rideguide.converter;

import org.mapstruct.Mapper;

import com.rideguide.entity.DriverInformation;
import com.rideguide.request.dto.DriverInformationDto;

@Mapper
public interface DriverInformationMapper {
    
    DriverInformation convertToEntity(final DriverInformationDto source);
   
}
