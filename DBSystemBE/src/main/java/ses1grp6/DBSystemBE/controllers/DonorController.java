package ses1grp6.DBSystemBE.controllers;

import ses1grp6.DBSystemBE.model.Donor;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Iterable<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Donor getByUserId(@PathVariable("id") int userId) {
        Donor donorById = donorRepository.findById(userId).get();
        return donorById;
    }

    @RequestMapping(value = "email/{email}", method = RequestMethod.GET)
    public @ResponseBody Donor getByEmail(@PathVariable("email") String email) {
       Donor donorByEmail = donorRepository.findByEmail(email);
       return donorByEmail;
    }
}
