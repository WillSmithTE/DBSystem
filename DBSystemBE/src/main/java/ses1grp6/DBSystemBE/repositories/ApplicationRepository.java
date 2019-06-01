package ses1grp6.DBSystemBE.repositories;

import org.springframework.data.repository.CrudRepository;
import ses1grp6.DBSystemBE.model.Application;

/**
 * Created by Will Smith on 4/4/19.
 */

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    
  Iterable<Application> findByDonorId(int donorId);

  Iterable<Application> findByCharityListingId(int listingId);

}
