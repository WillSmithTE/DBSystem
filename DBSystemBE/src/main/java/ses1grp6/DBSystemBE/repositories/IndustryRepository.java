package ses1grp6.DBSystemBE.repositories;

import org.springframework.data.repository.CrudRepository;
import ses1grp6.DBSystemBE.model.Industry;

/**
 * Created by Will Smith on 22/5/19.
 */
public interface IndustryRepository extends CrudRepository<Industry, Integer> {
    Iterable<Industry> findByIndustryName(String name);
}
