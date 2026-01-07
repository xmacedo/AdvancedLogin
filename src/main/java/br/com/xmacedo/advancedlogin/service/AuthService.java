package br.com.xmacedo.advancedlogin.service;

import br.com.xmacedo.advancedlogin.model.LoginAttempt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class AuthService {

    private BanService banService;
    private BehaviorScoringService behaviorScoringService;

    public AuthService(BanService banService) {
        this.banService = banService;
    }

    public ResponseEntity<?> authenticate(String email, String password, String fp) {

        LoginAttempt attempt = buildLoginAttenpt();

        double score = behaviorScoringService.score(attempt);

        if(score> 0.7 ){
            banService.tempBan(fp, Duration.ofMinutes(15));
        }
        
        return ResponseEntity.status(200).body("Login successful");
    }

    private LoginAttempt buildLoginAttenpt() {
        return new LoginAttempt();
    }
}
