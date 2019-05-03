package ses1grp6.DBSystemBE.repositories;

import org.springframework.data.repository.CrudRepository;
import ses1grp6.DBSystemBE.model.CharityUser;

/**
 * Created by Will Smith on 4/4/19.
 */

public interface CharityUserRepository extends CrudRepository<CharityUser, Integer> {
    
    // CharityUser findByCharityName(String charityName);

    CharityUser findByLastName(String lastName);
    
    Iterable<CharityUser> findByLocationID(int locationID);


}
