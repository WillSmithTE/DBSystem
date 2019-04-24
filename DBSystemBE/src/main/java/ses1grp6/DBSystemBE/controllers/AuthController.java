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
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

             sendConfirmationEmail(registrationRequest.getEmail(), newUser.getUserId());

             return Response.success(newUser);
         } else {
             return Response.fail("Email taken.");
         }
     }

     public void sendConfirmationEmail(String emailAddress, int userId){
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

    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
    public @ResponseBody Response confirmEmailById(@PathVariable("id") int userId) {
        Donor donor = donorRepository.findById(userId).get();
        donor.setEmailConfirmed(1);
        donorRepository.save(donor);
        return Response.success(donor);
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
