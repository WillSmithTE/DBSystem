package ses1grp6.DBSystemBE.controllers;

import ses1grp6.DBSystemBE.model.Donor;
import ses1grp6.DBSystemBE.model.Response;
import ses1grp6.DBSystemBE.repositories.DonorRepository;
import ses1grp6.DBSystemBE.model.RegistrationRequest;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private DonorRepository donorRepository;

     @PostMapping(value = "/register")
     public Response register(@RequestBody RegistrationRequest registrationRequest) {
         Donor preExistingUser = donorRepository.findByEmail(registrationRequest.getEmail());
         if (preExistingUser == null) {
             Donor newUser = donorRepository.save(new Donor(registrationRequest));
             return Response.success(newUser);
         } else {
             return Response.fail("Email taken.");
         }
     }

    // @PostMapping(value = "/login")
    // public Response login(@RequestBody LoginRequest loginRequest) {
    //     Donor preExistingUser = donorRepository.findByEmail(loginRequest.getEmail());
    //     if (preExistingUser.getPassword().equals(loginRequest.getPassword())) {
    //         if (preExistingUser.isEmailConfirmed()) {
    //             return Response.success(AuthController.createToken());
    //         } else {
    //             return Response.fail("Email not confirmed. Confirmation email resent.");
    //         }
    //     } else {
    //         return Response.fail("Login failed.");
    //     }
    // }

    private static String createToken() {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(algorithm, "herpderp");
//        do the things send a token
        return builder.compact();
    }

}
