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
    public @ResponseBody
    Donor getById(@PathVariable("id") int id) {
        return donorRepository.findById(id).get();
    }


    // @RequestMapping(value = "/{firstName}", method = RequestMethod.GET)
    // public @ResponseBody Donor getByFirstName(@PathVariable("firstName") String firstName) {
    //    List<Donor> donorsByFirstName = donorRepository.findByFirstName(firstName);
    //    return donorsByFirstName;
    // }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public User createUser(@Valid @RequestBody User user) {
//        user.set_id(ObjectId.get());
//        donorRepository.save(user);
//        return user;
//    }
}
