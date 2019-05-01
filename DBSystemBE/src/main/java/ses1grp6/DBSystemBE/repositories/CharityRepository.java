package ses1grp6.DBSystemBE.repositories;

import ses1grp6.DBSystemBE.model.Charity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Will Smith on 4/4/19.
 */

public interface CharityRepository extends CrudRepository<Charity, Integer> {
    
    Charity findByCharityName(String charityName);
    
    Iterable<Charity> findByLocationID(int locationID);


}
