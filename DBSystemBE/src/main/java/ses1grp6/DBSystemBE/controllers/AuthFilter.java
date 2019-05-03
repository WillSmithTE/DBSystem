package ses1grp6.DBSystemBE.controllers;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Will Smith on 10/4/19.
 */

@Component
public class AuthFilter extends OncePerRequestFilter {

    private static AntPathMatcher pathMatcher = new AntPathMatcher();

    private Collection<String> excludeUrlPatterns = new ArrayList<>();
//    private U
    public AuthFilter() {
        this.excludeUrlPatterns.add("/auth/**");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return this.excludeUrlPatterns.stream().anyMatch((url) -> pathMatcher.match(url, request.getServletPath()));
    }
}
