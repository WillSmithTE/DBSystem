package ses1grp6.DBSystemBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.*;
import ses1grp6.DBSystemBE.model.Application;
import ses1grp6.DBSystemBE.model.CharityListing;
import ses1grp6.DBSystemBE.model.Response;
import ses1grp6.DBSystemBE.repositories.ApplicationRepository;
import ses1grp6.DBSystemBE.repositories.CharityListingRepository;
import ses1grp6.DBSystemBE.repositories.IndustryRepository;

import javax.websocket.server.PathParam;

import static ses1grp6.DBSystemBE.model.Response.success;

/**
 * Created by Will Smith on 22/5/19.
 */

@RestController
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    CharityListingRepository listingRepository;

    @Autowired
    IndustryRepository industryRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    Response getAll() {
        try {
            return Response.success(listingRepository.findAll());
        } catch (Exception e) {
            return Response.fail("Failed to get listing: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public @ResponseBody
    Response create(@RequestBody CharityListing listing) {
        try {
            return Response.success(listingRepository.save(listing));
        } catch (Exception e) {
            return Response.fail("Failed to create listing: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response get(@PathParam("id") int id) {
        try {
            return success(listingRepository.findById(id).get());
        } catch (TransactionException e) {
            return Response.fail("Failed to get listing: " + e.getMessage());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody
    Response edit(@RequestBody CharityListing listing) {
        try {
            return Response.success(listingRepository.save(listing));
        } catch (TransactionException e) {
            return Response.fail("Failed to updating listing: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/{charityId}", method = RequestMethod.GET)
    public @ResponseBody
    Response getByCharityId(@PathParam("charityId") int charityId) {
        try {
            return Response.success(listingRepository.findByCharity(charityId));
        } catch (Exception e) {
            return Response.fail("Failed to fetch listing for charity: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public @ResponseBody
    Response apply(@RequestBody Application application) {
        try {
            return Response.success(applicationRepository.save(application));
        } catch (Exception e) {
            return Response.fail("Failed to apply for listing: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/applications/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response getApplicationsForListing(@PathParam("id") int id) {
        try {
            return Response.success(applicationRepository.findByCharityListingCharityId(id));
        } catch (Exception e) {
            return Response.fail("Failed to fetch applications for listing: " + e.getMessage());
        }
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public @ResponseBody Response<Iterable<CharityListing>> search(@RequestParam("search") String searchTerm) {
//        Set<CharityListing> matchedListings = new HashSet<>();
//
//        Iterable<Industry> matchedIndustries = industryRepository.
//
////        matchedListings.addAll()
//    }
}



