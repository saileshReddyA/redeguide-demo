package com.rideguide.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
public class SuccessResponse {
    private Map<String, String> data;
}
