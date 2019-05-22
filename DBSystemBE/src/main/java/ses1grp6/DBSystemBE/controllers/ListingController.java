package ses1grp6.DBSystemBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ses1grp6.DBSystemBE.model.CharityListing;
import ses1grp6.DBSystemBE.repositories.CharityListingRepository;

/**
 * Created by Will Smith on 22/5/19.
 */

@RestController
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    CharityListingRepository listingRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Iterable<CharityListing> getAll() {
        return listingRepository.findAll();
    }
}


