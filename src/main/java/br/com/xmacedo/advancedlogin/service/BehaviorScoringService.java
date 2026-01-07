package br.com.xmacedo.advancedlogin.service;

import br.com.xmacedo.advancedlogin.model.LoginAttempt;

public class BehaviorScoringService {
    public double score(LoginAttempt attempt) {
        double score = 0.0;
        if (attempt.getFailuresLast5m() > 3) {
            score += 0.3;
        }
        if (attempt.getUserAgentEntropy() < 0.6) {
            score += 0.2;
        }
        if (attempt.getGeoImpossible()) {
            score += 0.3;
        }

        if (attempt.getVelocity() > 8) {
            score += 0.4;
        }

        return score;
    }
}
