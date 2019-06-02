package ses1grp6.DBSystemBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.*;
import ses1grp6.DBSystemBE.controllers.EmailController;
import ses1grp6.DBSystemBE.model.ResponseStatus;
import ses1grp6.DBSystemBE.model.*;
import ses1grp6.DBSystemBE.repositories.CharityRepository;
import ses1grp6.DBSystemBE.repositories.DonorRepository;
import ses1grp6.DBSystemBE.repositories.UserRepository;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Will Smith on 4/4/19.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final static String REGISTER_PATH = "/register";
    public final static byte[] key = "3237866954478902".getBytes(Charset.defaultCharset());
    public final static byte[] iv = "5315253035103953".getBytes(Charset.defaultCharset());

    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private CharityRepository charityRepository;

    private static SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
    private static IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

    @PostMapping(value = AuthController.REGISTER_PATH)
    public Response register(@RequestBody RegistrationRequest registrationRequest) {
        if (registrationRequest.isCharity()) {
            return register(registrationRequest.getEmail(), charityRepository, () -> new Charity(registrationRequest));
        } else {
            return register(registrationRequest.getEmail(), donorRepository, () -> new Donor(registrationRequest));
        }
    }

    @RequestMapping(value = "confirm/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response donorConfirmEmailById(@PathVariable("id") int id) {
        Response confirmEmailResponse = confirmEmail(donorRepository, id);
        if (confirmEmailResponse.getStatus() == ResponseStatus.FAIL) {
            return confirmEmail(charityRepository, id);
        } else {
            return confirmEmailResponse;
        }
    }

    private <S extends User, T extends UserRepository<S>> Response confirmEmail(T repository, int id) {
        Optional<S> maybeUser = repository.findById(id);
        if (maybeUser.isPresent()) {
            S user = maybeUser.get();
            user.confirmEmail();
            repository.save(user);
            return Response.success(user);
        } else {
            return Response.fail("Error confirming email - couldn't find email with id " + id);
        }
    }

    @PostMapping(value = "/login")
    public Response login(@RequestBody LoginRequest loginRequest) {
        Response loginResponse = tryLogin(donorRepository, loginRequest, "donor");
        if (loginResponse.getStatus() == ResponseStatus.FAIL) {
            return tryLogin(charityRepository, loginRequest, "charity");
        } else {
            return loginResponse;
        }
    }

    private <T extends UserRepository> Response tryLogin(T repository, LoginRequest loginRequest, String userType) {
        User preExistingUser = repository.findByEmail(loginRequest.getEmail());
        if (preExistingUser != null && preExistingUser.getPassword().equals(loginRequest.getPassword())) {
            if (preExistingUser.isEmailConfirmed()) {
                return AuthController.createLoginSuccessObject(loginRequest, userType, preExistingUser.getId());
            } else {
                new EmailController().sendConfirmationEmail(loginRequest.getEmail(), preExistingUser.getId());
                return Response.fail("Email not confirmed. Confirmation email resent.");
            }
        } else {
            return Response.fail("Login failed.");
        }
    }

    private <T extends UserRepository> Response register(String email, T repository, Supplier<User> userGenerator) {
        User preExistingUser = repository.findByEmail(email);
        if (preExistingUser == null) {
            try {
                User newUser = (User) repository.save(userGenerator.get());
                new EmailController().sendConfirmationEmail(email, newUser.getId());
                return Response.success(newUser);
            } catch (TransactionException e) {
                return Response.fail("Failed to register: " + e.getMessage());
            }
        } else {
            return Response.fail("Email taken.");
        }

    }

    private static Response createLoginSuccessObject(LoginRequest request, String userType, int id) {
        byte[] token = AuthController.createToken(request.getEmail());
        return Response.success(new LoginResponse(token, userType, id));
    }


    private static byte[] createToken(String email) throws SecurityException {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(email.getBytes(Charset.defaultCharset()));
        } catch (BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new SecurityException("Failed to create login token: " + e.getMessage());
        }
    }

    public static String decryptToken(String token) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decryptedToken = cipher.doFinal(Base64.getDecoder().decode(token));
            return new String(decryptedToken);
        } catch (BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            throw new SecurityException("Failed to verify login token.");
        }
    }

}
