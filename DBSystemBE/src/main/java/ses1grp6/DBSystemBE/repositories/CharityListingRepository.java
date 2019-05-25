package ses1grp6.DBSystemBE.repositories;

import org.springframework.data.repository.CrudRepository;
import ses1grp6.DBSystemBE.model.Charity;
import ses1grp6.DBSystemBE.model.CharityListing;

/**
 * Created by Will Smith on 22/5/19.
 */
public interface CharityListingRepository extends CrudRepository<CharityListing, Integer> {

    Iterable<CharityListing> findByCharity(Charity charity);

    Iterable<CharityListing> findByListingTitleContainingIgnoreCase(String title);

    Iterable<CharityListing> findByListingDescriptionContainingIgnoreCase(String searchTerm);

    Iterable<CharityListing> findByLocationContainingIgnoreCase(String maybeSearchTerm);

    Iterable<CharityListing> findByCharityNameContainingIgnoreCase(String maybeSearchTerm);
}
