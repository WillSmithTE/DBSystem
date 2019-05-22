package ses1grp6.DBSystemBE.controllers;

import ses1grp6.DBSystemBE.model.Charity;
import ses1grp6.DBSystemBE.model.CharityUser;
import ses1grp6.DBSystemBE.repositories.CharityRepository;
import ses1grp6.DBSystemBE.repositories.CharityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/charity")
public class CharityController {

    @Autowired
    private CharityRepository charityRepository;
    @Autowired
    private CharityUserRepository charityUserRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Iterable<Charity> getAllCharities() {
        return charityRepository.findAll();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public @ResponseBody Charity getByCharityId(@PathVariable("id") int charityID) {
        Charity charityById = charityRepository.findById(charityID).get();
        return charityById;
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public @ResponseBody Charity getByCharityName(@PathVariable("name") String charityName) {
       Charity charityByName = charityRepository.findByName(charityName);
       return charityByName;
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody CharityUser getByCharityUserID(@PathVariable("id") int charityUserID) {
    CharityUser charityUserByID = charityUserRepository.findById(charityUserID).get();
    return charityUserByID;
    }
    
    @RequestMapping(value = "/user/name/{lastName}", method = RequestMethod.GET)
    public @ResponseBody CharityUser getByCharityUserName(@PathVariable("lastName") String lastName) {
    CharityUser charityUserByName = charityUserRepository.findByLastName(lastName);
    return charityUserByName;
    }


}