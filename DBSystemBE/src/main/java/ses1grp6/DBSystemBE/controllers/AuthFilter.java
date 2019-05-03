package ses1grp6.DBSystemBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ses1grp6.DBSystemBE.model.Charity;
import ses1grp6.DBSystemBE.model.Donor;
import ses1grp6.DBSystemBE.repositories.CharityRepository;
import ses1grp6.DBSystemBE.repositories.DonorRepository;

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

    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private CharityRepository charityRepository;

    private static AntPathMatcher pathMatcher = new AntPathMatcher();

    private Collection<String> excludeUrlPatterns = new ArrayList<>();

    public AuthFilter() {
        this.excludeUrlPatterns.add("/auth/**");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader("Authorization").substring(7);
        String decryptedToken = AuthController.decryptToken(authToken);
        Charity maybeCharity = charityRepository.findByEmail(decryptedToken);
        if (maybeCharity == null) {
            Donor maybeDoner = donorRepository.findByEmail(decryptedToken);
            if (maybeDoner == null) {
                throw new IOException("Invalid auth details.");
            }
        }
        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return this.excludeUrlPatterns.stream().anyMatch((url) -> pathMatcher.match(url, request.getServletPath()));
    }

}
