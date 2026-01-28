package com.auto.Job_Apply.service;
import com.auto.Job_Apply.config.JwtUtil;
import com.auto.Job_Apply.dto.request.LoginRequest;
import com.auto.Job_Apply.dto.request.LoginResponse;
import com.auto.Job_Apply.dto.request.RegisterRequest;
import com.auto.Job_Apply.dto.request.UserDto;
import com.auto.Job_Apply.entity.User;
import com.auto.Job_Apply.repository.UserRepository;
import com.auto.Job_Apply.exception.AuthException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public LoginResponse login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new AuthException("Invalid email or password"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid email or password");
        }

        String token = JwtUtil.generateToken(user.getEmail());

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());

        LoginResponse res = new LoginResponse();
        res.setToken(token);
        res.setUser(dto);

        return res;
    }
    public UserDto register(RegisterRequest req) {
        // Check if email already exists
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Hash the password
        String hashedPassword = encoder.encode(req.getPassword());

        // Create user entity
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(hashedPassword);
        user.setName(req.getName());

        // Save user to database
        userRepo.save(user);

        // Map to DTO
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());

        return dto;
    }
}
