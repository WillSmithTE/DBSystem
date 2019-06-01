package ses1grp6.DBSystemBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import ses1grp6.DBSystemBE.model.Charity;
import ses1grp6.DBSystemBE.model.Response;
import ses1grp6.DBSystemBE.repositories.CharityListingRepository;
import ses1grp6.DBSystemBE.repositories.CharityRepository;

@RestController
@RequestMapping("/charity")
public class CharityController {

    @Autowired
    private CharityRepository charityRepository;

    @Autowired
    private CharityListingRepository listingRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    Response getAllCharities() {
        try {
            return Response.success(charityRepository.findAll());
        } catch (Exception e) {
            return Response.fail("Failed to fetch all charities: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response getByCharityId(@PathVariable("id") int charityID) {
        try {
            return Response.success(charityRepository.findById(charityID).get());
        } catch (Exception e) {
            return Response.fail("Failed to get charity of id " + charityID + ": " + e.getMessage());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody Response edit(@RequestBody Charity charity) {
        try {
            return Response.success(charityRepository.save(charity));
        } catch (TransactionSystemException e) {
            return Response.fail("Failed to update charity, probably missing properties: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return Response.fail("Failed to update charity: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
    public @ResponseBody Response getHistory(@PathVariable("id") int id) {
        try {
            return Response.success(listingRepository.findByCharity(new Charity(id)));
        } catch (Exception e) {
            return Response.fail("Failed to get history for charity: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Response getByCharityName(@PathVariable("name") String charityName) {
        try {
            return Response.success(charityRepository.findByName(charityName));
        } catch (Exception e) {
            return Response.fail("Failed to fetch charity of name " + charityName + ": " + e.getMessage());
        }
    }


}