package back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import back.dto.LoginRequest;
import back.dto.LoginResponse;
import back.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/student/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.loginStudent(request);
    }
}