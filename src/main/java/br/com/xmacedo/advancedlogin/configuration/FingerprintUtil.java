package br.com.xmacedo.advancedlogin.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;

public class FingerprintUtil {
    public static String generateFingerPrint(HttpServletRequest request) {
        String data = request.getHeader("User-Agent") +
                request.getRemoteAddr() +
                request.getHeader("Accept-Language") +
                request.getHeader("Accept") +
                request.getHeader("Sec-Ch-UA");
        return DigestUtils.md5DigestAsHex(data.getBytes(StandardCharsets.UTF_8));
    }
}
