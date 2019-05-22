package ses1grp6.DBSystemBE.repositories;

import ses1grp6.DBSystemBE.model.Charity;

/**
 * Created by Will Smith on 4/4/19.
 */

public interface CharityRepository extends UserRepository<Charity> {
    
    Charity findByName(String name);
    
}
