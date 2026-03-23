package back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import back.dto.LoginRequest;
import back.dto.LoginResponse;
import back.entity.Student;
import back.repository.StudentRepository;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepo;

    public LoginResponse loginStudent(LoginRequest request) {

        Optional<Student> optionalStudent =
                studentRepo.findByEmail(request.getEmail());

        if (optionalStudent.isEmpty()) {
            return new LoginResponse(false, "User not found");
        }

        Student student = optionalStudent.get();

        if (!student.getPassword().equals(request.getPassword())) {
            return new LoginResponse(false, "Invalid password");
        }

        return new LoginResponse(
                true,
                "Login successful",
                student.getId(),
                student.getName()
        );
    }
}