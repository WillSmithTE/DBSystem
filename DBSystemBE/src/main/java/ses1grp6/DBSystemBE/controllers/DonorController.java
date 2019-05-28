package ses1grp6.DBSystemBE.controllers;

import org.springframework.transaction.TransactionException;
// import ses1grp6.DBSystemBE.model.Donation;
import org.springframework.transaction.TransactionSystemException;
import ses1grp6.DBSystemBE.model.Donor;
import ses1grp6.DBSystemBE.model.Response;
// import ses1grp6.DBSystemBE.repositories.DonationRepository;
import ses1grp6.DBSystemBE.repositories.ApplicationRepository;
import ses1grp6.DBSystemBE.repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Will Smith on 16/3/19.
 */

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/donor")
public class DonorController {
    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    Response getAllDonors() {
        try {
            return Response.success(donorRepository.findAll());
        } catch (Exception e) {
            return Response.fail("Failed to get all donors: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response getByUserId(@PathVariable("id") int userId) {
        try {
            return Response.success(donorRepository.findById(userId).get());
        } catch (Exception e) {
            return Response.fail("Failed to get donor of id " + userId + ": " + e.getMessage());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody
    Response editUser(@RequestBody Donor donor) {
        try {
            return Response.success(donorRepository.save(donor));
        } catch (TransactionSystemException e) {
            return Response.fail("Failed to update donor, probably missing properties: " + e.getCause().getCause().getMessage());
        } catch (TransactionException e) {
            return Response.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/history/{id}")
    public @ResponseBody
    Response getHistory(@PathVariable("id") int id) {
        try {
            return Response.success(applicationRepository.findByDonorId(id));
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }
}

