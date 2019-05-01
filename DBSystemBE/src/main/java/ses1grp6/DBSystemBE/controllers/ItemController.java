package ses1grp6.DBSystemBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ses1grp6.DBSystemBE.model.Item;
import ses1grp6.DBSystemBE.repositories.ItemRepository;

/**
 * Created by Will Smith on 12/4/19.
 */

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(value="")
    public @ResponseBody Iterable<Item> findItem(
            @RequestParam("location") String locationSearchTerm,
            @RequestParam("name") String name) {
return null;
//        Iterable<Item> = itemRepository.
    }
}
