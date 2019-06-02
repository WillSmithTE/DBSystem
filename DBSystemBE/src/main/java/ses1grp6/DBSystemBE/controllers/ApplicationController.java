package ses1grp6.DBSystemBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ses1grp6.DBSystemBE.model.Application;
import ses1grp6.DBSystemBE.model.Response;
import ses1grp6.DBSystemBE.repositories.ApplicationRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Will Smith on 29/5/19.
 */

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationRepository applicationRepository;

    @Transactional
    @RequestMapping(value = "accept/{id}", method = RequestMethod.GET)
    public Response acceptApplication(@PathVariable("id") Long id) {
        try {
            Optional<Application> maybeApplication = applicationRepository.findById(id);
            if (maybeApplication.isPresent()) {
                Application application = maybeApplication.get();
                application.accept();
                new EmailController().sendEmailAcceptance(maybeApplication.get());
                return Response.success(applicationRepository.save(application));
            } else {
                return Response.fail("Application of id " + id + " not found.");
            }
        } catch (Exception e) {
            return Response.fail("Failed to accept application of id " + id + ": " + e.getMessage());
        }
    }

    @Transactional
    @RequestMapping(value = "reject/{id}", method = RequestMethod.GET)
    public Response rejectApplication(@PathVariable("id") Long id) {
        try {
            Optional<Application> maybeApplication = applicationRepository.findById(id);
            if (maybeApplication.isPresent()) {
                Application application = maybeApplication.get();
                application.reject();
                return Response.success(applicationRepository.save(application));
            } else {
                return Response.fail("Application of id " + id + " not found.");
            }
        } catch (Exception e) {
            return Response.fail("Failed to accept application of id " + id + ": " + e.getMessage());
        }
    }
}
