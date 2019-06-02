package ses1grp6.DBSystemBE.controllers;

import ses1grp6.DBSystemBE.model.Application;
import ses1grp6.DBSystemBE.model.Charity;
import ses1grp6.DBSystemBE.model.CharityListing;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;
import java.util.Properties;

public class EmailController {
    private final static String USERNAME = "SES6donorAPP@gmail.com";
    private final static String PASSWORD = "Mdy6Rb5axNpkS8Gh";
    private final static String URL_PATH = "https://dbsystem.herokuapp.com";

    Properties prop;
    Session session;

    public EmailController() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
    }

    public void sendConfirmationEmail(String emailAddress, Integer userId) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailAddress)
            );
            message.setSubject("Email Confirmation Donor App");
            message.setText("Click the link to confirm your email.\n" + URL_PATH + "/auth/confirm/" + userId);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailAcceptance(Application application){

        String emailAddress = application.getDonor().getEmail();
        String name = application.getDonor().getName();
        String listingTitle = application.getCharityListing().getListingTitle();
        String charity = application.getCharity().getName();

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailAddress)
            );
            message.setSubject("Booking Confirmed  for " + listingTitle + " Donor App");
            message.setText("Hi " + name + ",\nYour booking request for " + listingTitle + " has been accepted.\nKind regards,\n" + charity);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
