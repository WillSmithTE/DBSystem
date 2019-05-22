package ses1grp6.DBSystemBE.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ses1grp6.DBSystemBE.model.User;

/**
 * Created by Will Smith on 3/5/19.
 */

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T, Integer> {
    T findByEmail(String email);
}
