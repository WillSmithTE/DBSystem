package ses1grp6.DBSystemBE.repositories;
import java.util.List;
import ses1grp6.DBSystemBE.model.Donors;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Will Smith on 16/3/19.
 */

public interface DonorRepository extends CrudRepository<Donors, Integer> {
    
    List<Donors> findByEmail(String email);

    List<Donors> findByFirstName(String firstName);

    Iterable<Donors> findByLocationId(int locationId);
}
