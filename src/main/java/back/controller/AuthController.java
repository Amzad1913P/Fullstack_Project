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

    // STUDENT LOGIN
    @PostMapping("/student/login")
    public LoginResponse studentLogin(@RequestBody LoginRequest request) {
        return authService.loginStudent(request);
    }

    // ADMIN LOGIN
    @PostMapping("/admin/login")
    public LoginResponse adminLogin(@RequestBody LoginRequest request) {
        return authService.loginAdmin(request);
    }
}