package com.willsmithte.DBSystemBE.repositories;

import com.willsmithte.DBSystemBE.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Will Smith on 16/3/19.
 */

public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(int id);

    User findByEmail(String email);
}
