package back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import back.dto.LoginRequest;
import back.dto.LoginResponse;
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

    // ==========================
    // STUDENT LOGIN
    // ==========================
    public LoginResponse loginStudent(LoginRequest request) {

        Optional<Student> optional =
                studentRepo.findByEmail(request.getEmail());

        if (optional.isEmpty()) {
            return new LoginResponse(false, "User not found");
        }

        Student student = optional.get();

        if (!student.getPassword().equals(request.getPassword())) {
            return new LoginResponse(false, "Invalid password");
        }

        return new LoginResponse(
                true,
                "Login successful",
                student.getId(),
                student.getName(),
                "STUDENT"
        );
    }

    // ==========================
    // ADMIN LOGIN
    // ==========================
    public LoginResponse loginAdmin(LoginRequest request) {

        Optional<Admin> optional =
                adminRepo.findByEmail(request.getEmail());

        if (optional.isEmpty()) {
            return new LoginResponse(false, "Admin not found");
        }

        Admin admin = optional.get();

        if (!admin.getPassword().equals(request.getPassword())) {
            return new LoginResponse(false, "Invalid password");
        }

        return new LoginResponse(
                true,
                "Admin login successful",
                admin.getId(),
                admin.getName(),
                "ADMIN"
        );
    }
}