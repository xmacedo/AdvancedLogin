package br.com.xmacedo.advancedlogin.filter;

import br.com.xmacedo.advancedlogin.configuration.FingerprintUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class FingerprintFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String fingerPrint = FingerprintUtil.generateFingerPrint(request);
        request.setAttribute("fp", fingerPrint);

        filterChain.doFilter(request, response);
    }
}
