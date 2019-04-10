package com.willsmithte.DBSystemBE.repositories;

import com.willsmithte.DBSystemBE.model.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Will Smith on 4/4/19.
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {
    Item findById(int id);
}
