package com.example.futureskills.controller;

import com.example.futureskills.dto.request.AuthenticationRequest;
import com.example.futureskills.dto.request.IntrospectRequest;
import com.example.futureskills.dto.response.ApiResponse;
import com.example.futureskills.dto.response.AuthenticationResponse;
import com.example.futureskills.dto.response.IntrospectResponse;
import com.example.futureskills.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
       var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result=authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
