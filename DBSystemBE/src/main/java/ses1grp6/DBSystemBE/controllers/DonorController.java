package ses1grp6.DBSystemBE.controllers;
import java.util.List;
import ses1grp6.DBSystemBE.model.Donors;
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
    private DonorRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Iterable<Donors> getAllDonors() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Donors getById(@PathVariable("id") int id) {
        return repository.findById(id).get();
    }


    // @RequestMapping(value = "/{firstName}", method = RequestMethod.GET)
    // public @ResponseBody Donors getByFirstName(@PathVariable("firstName") String firstName) {
    //    List<Donors> donorsByFirstName = repository.findByFirstName(firstName);
    //    return donorsByFirstName;
    // }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public User createUser(@Valid @RequestBody User user) {
//        user.set_id(ObjectId.get());
//        repository.save(user);
//        return user;
//    }
}
