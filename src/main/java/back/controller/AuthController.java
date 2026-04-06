package back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import back.dto.LoginRequest;
import back.dto.LoginResponse;
import back.dto.UserDTO;
import back.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/student/login")
    public LoginResponse studentLogin(@RequestBody LoginRequest request) {
        return authService.loginStudent(request);
    }

    @PostMapping("/admin/login")
    public LoginResponse adminLogin(@RequestBody LoginRequest request) {
        return authService.loginAdmin(request);
    }

    @GetMapping("/me")
    public UserDTO getProfile(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return authService.getProfile(token);
        }
        return null;
    }
}