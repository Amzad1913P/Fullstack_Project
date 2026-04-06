package back.service;

import back.entity.Student;
import back.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        Optional<Student> studentOptional = studentRepo.findByEmail(email);

        if (studentOptional.isEmpty()) {
            Student student = new Student();
            student.setEmail(email);
            student.setName(name);
            student.setPassword(""); // OAuth2 users don't need a local password
            studentRepo.save(student);
        }

        return oauth2User;
    }
}
