package ses1grp6.DBSystemBE.model;

import javax.persistence.*;

/**
 * Created by Will Smith on 4/4/19.
 */

@javax.persistence.Entity
public class Donation {
    @Id
    private int id;
    private String dateTime;
    private String donorId;
    private String charityId;
}
