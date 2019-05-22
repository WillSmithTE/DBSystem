package ses1grp6.DBSystemBE.controllers;

import org.springframework.transaction.TransactionException;
// import ses1grp6.DBSystemBE.model.Donation;
import ses1grp6.DBSystemBE.model.Donor;
import ses1grp6.DBSystemBE.model.Response;
// import ses1grp6.DBSystemBE.repositories.DonationRepository;
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

    // @Autowired
    // private DonationRepository donationRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Iterable<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Donor getByUserId(@PathVariable("id") int userId) {
        Donor donorById = donorRepository.findById(userId).get();
        return donorById;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Response editUser(@PathVariable("id") int userId, @RequestParam Donor donor) {
        try {
            return Response.success(donorRepository.save(donor));
        } catch (TransactionException e) {
            return Response.fail(e.getMessage());
        }
    }

    // @RequestMapping(value = "/history/{id}")
    // public @ResponseBody Donation[] getHistory(@PathVariable("id") int id) {
    //     return donationRepository.findByDonorId(id);
    // }

    @RequestMapping(value = "email/{email}", method = RequestMethod.GET)
    public @ResponseBody Donor getByEmail(@PathVariable("email") String email) {
       Donor donorByEmail = donorRepository.findByEmail(email);
       return donorByEmail;
    }
}
