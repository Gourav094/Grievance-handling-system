package com.app.grievance.controller;
import com.app.grievance.dto.LoginRequest;
import com.app.grievance.dto.LoginResponse;
import com.app.grievance.dto.RegisterRequest;
import com.app.grievance.dto.RegisterResponse;
import com.app.grievance.exception.CustomException;
import com.app.grievance.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("Login request received for username: {}", request.getEmail());
        try {
            // Perform login logic
            LoginResponse response = authService.login(request);

            // Log the successful login response
            logger.info("Login successful for username: {}, returning response: {}", request.getEmail(), response);

            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            // Handle custom errors (e.g., user not found, invalid password)
            logger.warn("Custom error during login: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        } catch (Exception e) {
            // Log other unexpected errors
            logger.error("Unexpected error occurred during login for username: {}", request.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred during login");
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        // System.out.println("Register request: " + request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint");
    }
}
