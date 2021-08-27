package com.rideguide.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rideguide.entity.User;
import com.rideguide.entity.UserPasswordResetRequest;
import com.rideguide.request.dto.ForgotPasswordDto;
import com.rideguide.request.dto.ResetPasswordDto;
import com.rideguide.response.dto.SuccessResponse;
import com.rideguide.service.UserService;

import javax.validation.Valid;

import static com.rideguide.util.Constants.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "Password Reset")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/auth")
public class ResetPasswordController {

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;


    public ResetPasswordController(
            UserService userService,
            ApplicationEventPublisher eventPublisher
    ) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @ApiOperation(value = SWG_RESPWD_FORGOT_OPERATION, response = SuccessResponse.class)
    @PostMapping(value = "/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto)
            throws ResourceNotFoundException {
        User user = userService.getUserByEmail(forgotPasswordDto.getEmail());
        Map<String, String> result = new HashMap<>();

        if (user == null) {
            result.put(MESSAGE_KEY, NO_USER_FOUND_WITH_EMAIL_MESSAGE);
            return ResponseEntity.badRequest().body(result);
        }

        UserPasswordResetRequest userPasswordResetRequest = userService.createPasswordResetRequest(user.getEmail());

        //TODO: Send email with token

        result.put(MESSAGE_KEY, PASSWORD_LINK_SENT_MESSAGE);
        System.out.println("Password token " + userPasswordResetRequest.getExternalId());
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@Valid @RequestBody ResetPasswordDto passwordResetDto)
            throws ResourceNotFoundException {

        UserPasswordResetRequest userPasswordResetRequest = userService.getPasswordResetRequest(passwordResetDto.getToken());
        Map<String, String> result = new HashMap<>();

        if (userPasswordResetRequest.getExpireDateTime().toLocalDateTime().isBefore(LocalDateTime.now())) {
            result.put(MESSAGE_KEY, TOKEN_EXPIRED_MESSAGE);
            return ResponseEntity.badRequest().body(result);
        }

        userService.updatePassword(passwordResetDto.getToken(), passwordResetDto.getPassword());

        result.put(MESSAGE_KEY, RESET_PASSWORD_SUCCESS_MESSAGE);

        return ResponseEntity.badRequest().body(result);
    }
}
