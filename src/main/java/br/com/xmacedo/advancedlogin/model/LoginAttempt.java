package br.com.xmacedo.advancedlogin.model;

public class LoginAttempt {
    private Integer failuresLast5m;
    private Double userAgentEntropy;
    private Boolean geoImpossible;
    private Integer velocity;

    public Integer getFailuresLast5m() {
        return failuresLast5m;
    }

    public void setFailuresLast5m(Integer failuresLast5m) {
        this.failuresLast5m = failuresLast5m;
    }

    public Double getUserAgentEntropy() {
        return userAgentEntropy;
    }

    public void setUserAgentEntropy(Double userAgentEntropy) {
        this.userAgentEntropy = userAgentEntropy;
    }

    public Boolean getGeoImpossible() {
        return geoImpossible;
    }

    public void setGeoImpossible(Boolean geoImpossible) {
        this.geoImpossible = geoImpossible;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }
}
