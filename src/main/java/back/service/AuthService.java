package back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import back.dto.LoginRequest;
import back.dto.LoginResponse;
import back.dto.UserDTO;
import back.entity.Student;
import back.entity.Admin;
import back.repository.StudentRepository;
import back.repository.AdminRepository;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CaptchaService captchaService;

    public LoginResponse loginStudent(LoginRequest request) {
        if (!captchaService.verify(request.getCaptchaToken())) {
            return new LoginResponse(false, "Invalid CAPTCHA verification");
        }

        Optional<Student> optional = studentRepo.findByEmail(request.getEmail());
        if (optional.isEmpty()) {
            return new LoginResponse(false, "User not found");
        }

        Student student = optional.get();
        if (!student.getPassword().equals(request.getPassword())) {
            return new LoginResponse(false, "Invalid password");
        }

        String token = jwtService.generateToken(student.getEmail(), "STUDENT");
        return new LoginResponse(true, "Login successful", student.getId(), student.getName(), "STUDENT", token);
    }

    public LoginResponse loginAdmin(LoginRequest request) {
        if (!captchaService.verify(request.getCaptchaToken())) {
            return new LoginResponse(false, "Invalid CAPTCHA verification");
        }

        Optional<Admin> optional = adminRepo.findByEmail(request.getEmail());
        if (optional.isEmpty()) {
            return new LoginResponse(false, "Admin not found");
        }

        Admin admin = optional.get();
        if (!admin.getPassword().equals(request.getPassword())) {
            return new LoginResponse(false, "Invalid password");
        }

        String token = jwtService.generateToken(admin.getEmail(), "ADMIN");
        return new LoginResponse(true, "Admin login successful", admin.getId(), admin.getName(), "ADMIN", token);
    }

    public UserDTO getProfile(String token) {
        try {
            String email = jwtService.extractUsername(token);
            String role = jwtService.extractRole(token);

            if ("STUDENT".equals(role)) {
                return studentRepo.findByEmail(email)
                        .map(s -> new UserDTO(s.getId(), s.getName(), s.getEmail(), "STUDENT"))
                        .orElse(null);
            } else if ("ADMIN".equals(role)) {
                return adminRepo.findByEmail(email)
                        .map(a -> new UserDTO(a.getId(), a.getName(), a.getEmail(), "ADMIN"))
                        .orElse(null);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}