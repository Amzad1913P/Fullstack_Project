package back.config;

import back.entity.Student;
import back.repository.StudentRepository;
import back.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private StudentRepository studentRepo;

    // ✅ Dynamic frontend URL
    @Value("${frontend.url}")
    private String frontendUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");

        Optional<Student> studentOptional = studentRepo.findByEmail(email);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            String token = jwtService.generateToken(student.getEmail(), "STUDENT");

            String targetUrl = UriComponentsBuilder
                    .fromUriString(frontendUrl + "/login")
                    .queryParam("token", token)
                    .queryParam("userId", student.getId())
                    .queryParam("name", student.getName())
                    .queryParam("role", "student")
                    .build()
                    .toUriString();

            response.sendRedirect(targetUrl);
        } else {
            response.sendRedirect(frontendUrl + "/login?error=user_not_found");
        }
    }
}