package ses1grp6.DBSystemBE.controllers;

import ses1grp6.DBSystemBE.model.User;
import ses1grp6.DBSystemBE.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Will Smith on 16/3/19.
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User getById(@PathVariable("id")int id) {
        return repository.findById(id);
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public User createUser(@Valid @RequestBody User user) {
//        user.set_id(ObjectId.get());
//        repository.save(user);
//        return user;
//    }
}
