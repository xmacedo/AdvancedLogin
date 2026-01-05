package br.com.xmacedo.advancedlogin.controller;

import br.com.xmacedo.advancedlogin.model.LoginRequest;
import br.com.xmacedo.advancedlogin.service.RedisRateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private RedisRateLimiter rateLimiter;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest http) {
        String fp = (String) http.getAttribute("fp");
        if(!rateLimiter.allow(fp)){
            return ResponseEntity.status(429).body("Too many requests. Try again in 1 minute.");
        }
        //return authService.authenticate(req.email(), req.password(), fp);
        return ResponseEntity.status(200).body("Login successful");
    }
}
