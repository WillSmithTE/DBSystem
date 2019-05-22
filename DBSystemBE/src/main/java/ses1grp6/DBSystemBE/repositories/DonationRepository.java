package ses1grp6.DBSystemBE.repositories;

import org.springframework.data.repository.CrudRepository;
import ses1grp6.DBSystemBE.model.Donation;

/**
 * Created by Will Smith on 4/4/19.
 */

public interface DonationRepository extends CrudRepository<Donation, Integer> {
    Donation[] findByDonorId(int id);
}
