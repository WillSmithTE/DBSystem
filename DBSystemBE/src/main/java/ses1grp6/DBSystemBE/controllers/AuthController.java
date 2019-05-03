package ses1grp6.DBSystemBE.controllers;

import ses1grp6.DBSystemBE.model.*;
import ses1grp6.DBSystemBE.repositories.CharityRepository;
import ses1grp6.DBSystemBE.repositories.DonorRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ses1grp6.DBSystemBE.repositories.UserRepository;

import java.util.Date;
import java.util.Properties;
import java.util.function.Supplier;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Will Smith on 4/4/19.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final static String REGISTER_PATH = "/register";
    private final static String LOGIN_PATH = "/login";

    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private CharityRepository charityRepository;

    @PostMapping(value = AuthController.REGISTER_PATH)
    public Response register(@RequestBody RegistrationRequest registrationRequest) {
        if (registrationRequest.isCharity()) {
            return register(registrationRequest.getEmail(), charityRepository, () -> new Charity(registrationRequest));
        } else {
            return register(registrationRequest.getEmail(), donorRepository, () -> new Donor(registrationRequest));
        }
    }

    @RequestMapping(value = "charity/confirm/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Response charityConfirmEmailById(@PathVariable("id") int id) {
        return confirmEmail(charityRepository, id);
    }

    @RequestMapping(value = "donor/confirm/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Response donorConfirmEmailById(@PathVariable("id") int id) {
        return confirmEmail(donorRepository, id);
    }

    private <S extends User, T extends UserRepository<S>> Response confirmEmail(T repository, int id) {
        S user = repository.findById(id).get();
        user.confirmEmail();
        repository.save(user);
        return Response.success(user);
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

    private void sendConfirmationEmail(String emailAddress, Long userId) {
        final String username = "SES6donorAPP@gmail.com";
        final String password = "Mdy6Rb5axNpkS8Gh";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailAddress)
            );
            message.setSubject("Email Confirmation Donor App");
            message.setText("Click the link to confirm your email.\n" + "http://192.168.0.2:8080/auth/confirm/" + userId);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private <T extends UserRepository> Response register(String email, T repository, Supplier<User> userGenerator) {
         User preExistingUser = repository.findByEmail(email);
        if (preExistingUser == null) {
             User newUser = (User) repository.save(userGenerator.get());
            sendConfirmationEmail(email, newUser.getId());
            return Response.success(newUser);
        } else {
            return Response.fail("Email taken.");
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
