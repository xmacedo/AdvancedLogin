# Advanced Login Protection in Spring Boot (2025): Stop Credential Stuffing Using Rate-Limits + Fingerprints + ML

1. Fingerprinting (identify real device, not IP).
2. AI-based Anomaly Detection (detect bot behavior)
3. Redis Token Bucket Rate-Limiting (slow down attackers brutally)


## Part 1 — Device Fingerprinting (Far Better Than IP)

> Note: IP is useless in 2025.

- Attackers rotate proxies every 1–3 requests.
- Instead, we generate a stable, reusable device fingerprint using:

1. TLS signature (JA3/JA4)
2. User-Agent entropy score
3. Behavioral timing
4. Geo-stability
5. Header hash


### Spring Boot Fingerprint Filter

- [FingerprintFilter](src/main/java/br/com/xmacedo/advancedlogin/filter/FingerprintFilter.java)
- [FingerprintUtil](src/main/java/br/com/xmacedo/advancedlogin/configuration/FingerprintUtil.java)

[+] Works across Chrome, Firefox, apps <br>
[+] Stays stable even if proxy changes <br>
[+] Impossible to spoof without deep client spoofing

## Part 2 — Redis Rate Limiting (Per Fingerprint)

- Credential stuffing = thousands of attempts per minute.
- We slow attackers instantly.

### Token Bucket Lua Script (Atomic + Fast)
- [RedisRateLimiter](src/main/java/br/com/xmacedo/advancedlogin/service/RedisRateLimiter.java)

[+] Allow 5 login attempts per minute per fingerprint<br>
[+] Exponential backoff for suspicious behavior (later)

-> Apply Limit on Login Endpoint
- [LoginController](src/main/java/br/com/xmacedo/advancedlogin/controller/LoginController.java)


## Part 3 — ML Behavior Scoring (The Real Killer)
