package ses1grp6.DBSystemBE.controllers;

import ses1grp6.DBSystemBE.model.LoginRequest;
import ses1grp6.DBSystemBE.model.Response;
import ses1grp6.DBSystemBE.repositories.UserRepository;
import ses1grp6.DBSystemBE.model.RegistrationRequest;
import ses1grp6.DBSystemBE.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * Created by Will Smith on 4/4/19.
 */

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/register")
    public Response register(@RequestBody RegistrationRequest registrationRequest) {
        Key key = MacProvider.generateKey(SignatureAlgorithm.HS256);
        Key otherKeyThing = new SecretKeySpec(key.getEncoded(), SignatureAlgorithm.HS256.getJcaName());
        User preExistingUser = userRepository.findByEmail(registrationRequest.getEmail());
        if (preExistingUser == null) {
            User newUser = userRepository.save(new User(registrationRequest));
            return Response.success(newUser);
        } else {
            return Response.fail("Email taken.");
        }
    }

    @PostMapping(value = "/login")
    public Response login(@RequestBody LoginRequest loginRequest) {
        User preExistingUser = userRepository.findByEmail(loginRequest.getEmail());
        if (preExistingUser.getPassword().equals(loginRequest.getPassword())) {
            if (preExistingUser.isEmailConfirmed()) {
                return Response.success(AuthController.createToken());
            } else {
                return Response.fail("Email not confirmed. Confirmation email resent.");
            }
        } else {
            return Response.fail("Login failed.");
        }
    }

    private static String createToken() {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(algorithm, "herpderp");
//        do the things send a token
        return builder.compact();
    }

}
