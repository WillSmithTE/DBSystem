package ses1grp6.DBSystemBE.repositories;

import ses1grp6.DBSystemBE.model.Donor;

/**
 * Created by Will Smith on 16/3/19.
 */

public interface DonorRepository extends UserRepository<Donor> {
    
    Donor findByName(String name);
}
